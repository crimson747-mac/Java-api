package json.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import json.BookDto;

import java.util.ArrayList;
import java.util.List;

public class JsonA {

    public static void main(String[] args) {
        BookDto bookDto = new BookDto("자바", 21000, "에이콘", 670);
        Gson gson = new Gson();

        //obj -> json
        String json = gson.toJson(bookDto);
        System.out.println("json = " + json);

        //json -> obj
        BookDto dto = gson.fromJson(json, BookDto.class);
        System.out.println("dto = " + dto);
        System.out.println("title = " + dto.getTitle());
        System.out.println("price = " + dto.getPrice());

        //list -> json
        List<BookDto> bookDtoList = new ArrayList<>();
        bookDtoList.add(new BookDto("자바1", 21000, "에이콘", 670));
        bookDtoList.add(new BookDto("자바2", 11000, "에이콘", 270));
        bookDtoList.add(new BookDto("자바3", 31000, "에이콘", 870));

        String list = gson.toJson(bookDtoList);
        System.out.println("list = " + list);

        //json -> list
        List<BookDto> bookList = gson.fromJson(list, new TypeToken<List<BookDto>>(){}.getType());
        System.out.println(bookList);
    }

}
