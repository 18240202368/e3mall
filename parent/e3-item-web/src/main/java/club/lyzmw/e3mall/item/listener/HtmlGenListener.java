package club.lyzmw.e3mall.item.listener;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import club.lyzmw.e3mall.item.pojo.Item;
import club.lyzmw.e3mall.pojo.TbItem;
import club.lyzmw.e3mall.pojo.TbItemDesc;
import club.lyzmw.e3mall.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 监听商品添加消息，生成对应的静态页面
 * @author Administrator
 *
 */

public class HtmlGenListener implements MessageListener{
	@Autowired
	ItemService itemService;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Value("${HTML_GEN_PATH}")
	private String HTML_GEN_PATH;
	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			Long itemId = new Long(text);
			TbItem tbItem = itemService.getItemById(itemId);
			Item item = new Item(tbItem);
			TbItemDesc itemDesc = itemService.getItemDescById(itemId);
			Map data = new HashMap<>();
			data.put("item", item);
			data.put("itemDesc", itemDesc);
			//加载模板对象
			Configuration configuration = freeMarkerConfigurer.getConfiguration();
			Template template = configuration.getTemplate("item.ftl");
			//创建输出流
			Writer out = new FileWriter(HTML_GEN_PATH+itemId+".html");
			//生成静态页面
			template.process(data, out);
			//关闭流
			out.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
