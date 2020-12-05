package org.example;

import java.io.FileReader;
import java.util.Iterator;
import java.io.*;
import java.sql.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Connection conn = null;
        Statement st = null;

        String url = "jdbc:postgresql://127.0.0.1:5432/library";
        String user = "postgres";
        String password = "kw1996";
        try {
            conn = DriverManager.getConnection(url, user, password);
            st = conn.createStatement();

            CreateBookInfo createBookInfo = new CreateBookInfo();
            createBookInfo.createMethod();

        try {

            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(new FileReader("C:\\Users\\김기훈\\Desktop\\librarytxt.json"));
            //JSON데이터를 넣어 JSON Object 로 만들어 준다.
            JSONObject jsonObject = (JSONObject) obj;

            //books의 배열을 추출
            JSONArray bookInfoArray = (JSONArray) jsonObject.get("books");

            System.out.println("* BOOKS *");

            for (int i = 0; i < bookInfoArray.size(); i++) {
                String bookImageUrl;
                String volmCnt;
                String publcatnYy;
                String publshcmpyNm;
                String authorNmInfo;
                String bookNmInfo;
                String rkiNo;
                String stdYm;
                //System.out.println("=BOOK_" + i + " ===========================================");

                //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
                JSONObject bookObject = (JSONObject) bookInfoArray.get(i);
                //JSON name으로 추출
                //출력 확인용
                /*System.out.println("bookInfo: bookImageUrl==>" + bookObject.get("BOOK_IMAGE_URL"));
                System.out.println("bookInfo: volmCnt==>" + bookObject.get("VOLM_CNT"));
                System.out.println("bookInfo: publcatnYy==>" + bookObject.get("PUBLCATN_YY"));
                System.out.println("bookInfo: publshcmpyNm==>" + bookObject.get("PUBLSHCMPY_NM"));
                System.out.println("bookInfo: authorNmInfo==>" + bookObject.get("AUTHOR_NM_INFO"));
                System.out.println("bookInfo: bookNmInfo==>" + bookObject.get("BOOK_NM_INFO"));
                System.out.println("bookInfo: rkiNo==>" + bookObject.get("RKI_NO"));
                System.out.println("bookInfo: stdYm==>" + bookObject.get("STD_YM"));*/

                bookImageUrl = (String) bookObject.get("BOOK_IMAGE_URL");
                volmCnt = (String) bookObject.get("VOLM_CNT");
                publcatnYy = (String) bookObject.get("PUBLCATN_YY");
                publshcmpyNm = (String) bookObject.get("PUBLSHCMPY_NM");
                authorNmInfo = (String) bookObject.get("AUTHOR_NM_INFO");
                bookNmInfo = (String) bookObject.get("BOOK_NM_INFO");
                rkiNo = (String) bookObject.get("RKI_NO");
                stdYm = (String) bookObject.get("STD_YM");

                st.executeUpdate("insert into BookInfo values (\'"+stdYm+"\',\'"+rkiNo+"\',\'"+bookNmInfo+"\',\'"+authorNmInfo+"\',\'"+publshcmpyNm+"\',\'"+publcatnYy+"\',\'"+volmCnt+"\',\'"+bookImageUrl+"\')");


            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        catch (SQLException sqlEX) {
            System.out.println(sqlEX);
        }
    }
}
