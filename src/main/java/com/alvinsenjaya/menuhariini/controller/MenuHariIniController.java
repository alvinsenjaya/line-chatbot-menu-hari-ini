package com.alvinsenjaya.menuhariini.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.util.Arrays.asList;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;

import com.alvinsenjaya.menuhariini.model.MenuDetail;
import com.alvinsenjaya.menuhariini.model.MenuOverview;
import com.alvinsenjaya.menuhariini.service.MenuHariIniService;
import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.flex.component.Box;
import com.linecorp.bot.model.message.flex.component.FlexComponent;
import com.linecorp.bot.model.message.flex.component.Image;
import com.linecorp.bot.model.message.flex.component.Image.ImageAspectMode;
import com.linecorp.bot.model.message.flex.component.Image.ImageAspectRatio;
import com.linecorp.bot.model.message.flex.component.Image.ImageSize;
import com.linecorp.bot.model.message.flex.component.Separator;
import com.linecorp.bot.model.message.flex.component.Text;
import com.linecorp.bot.model.message.flex.component.Text.TextWeight;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;
import com.linecorp.bot.model.message.template.CarouselColumn;
import com.linecorp.bot.model.message.template.CarouselTemplate;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@LineMessageHandler
public class MenuHariIniController {
	
	@Autowired
	private MenuHariIniService service;

	@EventMapping
    public Message handleTextEvent(MessageEvent<TextMessageContent> messageEvent){
        String text = messageEvent.getMessage().getText().trim().toLowerCase();
        
        if(text.startsWith("/bantuan") || text.startsWith("/help") || text.startsWith("help") || text.startsWith("bantuan")){
        	return new TextMessage("Halooo! Kami siap untuk membantu Kamu dalam urusan memasak. Silahkan ketik dan kirim kata kunci makanan yang Kamu inginkan, dan kami akan menampilkan hasil pencariannya.");
        } else if(text.startsWith("/detail/")){
        	MenuDetail menuDetail = service.findMenuDetailByKey(text.substring(8));
        	if(menuDetail == null) {
        		return new TextMessage("Yahhhh! Kami belum dapat memproses pesan Kamu. Mohon dicoba beberapa saat lagi.");
        	} else {
        		return MenuDetailWithFlexMessage(menuDetail);
        	}
        } else {
        	List<MenuOverview> menuOverviewList = service.findMenuOverviewByTitle(text);
            
            if(menuOverviewList == null) {
            	return new TextMessage("Yahhhh! Kami belum dapat memproses pesan Kamu. Mohon dicoba beberapa saat lagi.");
            }
            else if(menuOverviewList.isEmpty()) {
            	return new TextMessage("Resep berdasarkan kata kunci '" + text + "' tidak ditemukan.");
            }
            else {
            	return MenuOverviewListWithCarousel(menuOverviewList);
            }
        }        
    }
	
	@EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
	
	public TemplateMessage MenuOverviewListWithCarousel(List<MenuOverview> menuOverviewList) {
		List<CarouselColumn> carouselColumn = new ArrayList<CarouselColumn>();
		int length = menuOverviewList.size();
		if(length > 10) {
			length = 10;
		}
		
		for(int i = 0; i < length; i++) {
			carouselColumn.add(
				new CarouselColumn(
					URI.create(menuOverviewList.get(i).getThumb()),
					menuOverviewList.get(i).getTitle().substring(6, Math.min(menuOverviewList.get(i).getTitle().length(), 46)), 
					"Waktu: " + menuOverviewList.get(i).getTimes() + "\nKesulitan: " + menuOverviewList.get(i).getDifficulty() + "\nPorsi: " + menuOverviewList.get(i).getServing(), 
					new ArrayList<Action>(Arrays.asList(new MessageAction("Tampilkan Resep", "/detail/" + menuOverviewList.get(i).getKey())))
				)
			);
		}
		
		CarouselTemplate carouselTemplate = new CarouselTemplate(carouselColumn);
		
        return new TemplateMessage("Hasil Pencarian Resep Makanan", carouselTemplate);
	}
	
	public FlexMessage MenuDetailWithFlexMessage(MenuDetail menuDetail) {
		List<FlexComponent> ingredients = new ArrayList<FlexComponent>();
		List<FlexComponent> steps = new ArrayList<FlexComponent>();
		
		for(int i = 0; i < menuDetail.getIngredient().size(); i++) {
			ingredients.add(
					Text.builder().text(menuDetail.getIngredient().get(i)).size(FlexFontSize.XS).wrap(true).build()
			);
		}
		
		for(int i = 0; i < menuDetail.getStep().size(); i++) {
			steps.add(
					Text.builder().text(menuDetail.getStep().get(i)).size(FlexFontSize.XS).wrap(true).build()
			);
		}
		
		return new FlexMessage("Resep Makanan", Bubble.builder()
				.hero(Image.builder()
						.url(URI.create(menuDetail.getThumb()))
						.size(ImageSize.FULL_WIDTH)
						.aspectRatio(ImageAspectRatio.R20TO13)
						.aspectMode(ImageAspectMode.Cover)
						.build())
				.body(Box.builder()
						.layout(FlexLayout.VERTICAL)
						.contents(asList(
								Text.builder().text(menuDetail.getTitle()).weight(TextWeight.BOLD).wrap(true).build(),
								Separator.builder().margin(FlexMarginSize.MD).build(),
								Text.builder().text("Tanggal Publikasi: " + menuDetail.getAuthor().getDatePublished()).size(FlexFontSize.XS).wrap(true).build(),
								Text.builder().text("Author: " + menuDetail.getAuthor().getUser()).size(FlexFontSize.XS).wrap(true).build(),
								Text.builder().text("Waktu: " + menuDetail.getTimes()).size(FlexFontSize.XS).wrap(true).build(),
								Text.builder().text("Porsi: " + menuDetail.getServings()).size(FlexFontSize.XS).wrap(true).build(),
								Text.builder().text("Kesulitan: " + menuDetail.getDificulty()).size(FlexFontSize.XS).wrap(true).build(),
								Separator.builder().margin(FlexMarginSize.MD).build(),
								Text.builder().text("Deskripsi").weight(TextWeight.BOLD).wrap(true).build(),
								Text.builder().text(menuDetail.getDesc()).size(FlexFontSize.XS).wrap(true).build(),
								Separator.builder().margin(FlexMarginSize.MD).build(),
								Text.builder().text("Bahan").weight(TextWeight.BOLD).wrap(true).build(),
								Box.builder().contents(ingredients).layout(FlexLayout.VERTICAL).build(),
								Separator.builder().margin(FlexMarginSize.MD).build(),
								Text.builder().text("Langkah Memasak").weight(TextWeight.BOLD).wrap(true).build(),
								Box.builder().contents(steps).layout(FlexLayout.VERTICAL).build()
						))
						.build()
				)
				.build()
		);
	}
}
