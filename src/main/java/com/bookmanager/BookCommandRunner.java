package com.bookmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bookmanager.dao.BookInfoRepository;
import com.bookmanager.domain.BookInfo;

@Component
public class BookCommandRunner implements CommandLineRunner {

	@Autowired
	BookInfoRepository bookInfoRepository;
	
	@Override
	public void run(String... arg0) throws Exception {
		BookInfo bookInfo;
		for (int i = 0; i < 20; i++) {
			bookInfo = new BookInfo();
			bookInfo.setAuthor("zhangsan");
			bookInfo.setContent("good book");
			bookInfo.setISBN("AHBS" + String.valueOf(i));
			bookInfo.setLink("linkAddress:" + String.valueOf(i));
			bookInfo.setTitle("sanguoyanyi" + String.valueOf(i));
			bookInfoRepository.save(bookInfo);
		}
	}

}
