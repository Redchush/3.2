package dao.impl;

import dao.IDao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by user on 28.05.2016.
 */
public class DaoImpl implements IDao {

    private String path;

    public DaoImpl(String path) {
        this.path = path;
    }

    @Override
    public String readSource() {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader filereader = new BufferedReader(new FileReader(path));
            String tempLine;
            while ((tempLine = filereader.readLine()) != null){
                builder.append(tempLine).append("\\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        builder.deleteCharAt(builder.length()-1);
        return normaliseString(new String(builder));
    }

    private String normaliseString(String sourse){
        String adoptSourse = sourse.replace(" = ", "=")
                .replace("= ", "=")
                .replace(" =", "=")
                .replace("\\n", "")
                .replace("< ", "<")
                .replace(" >", ">")
                .replace("/>", " />")
                .replace("> <", "><");
        while (adoptSourse.contains("  ")){
            adoptSourse = adoptSourse.replaceAll("  ", " ");
        }
        this.path = null;
        return adoptSourse;
        //    System.out.println("in adopted: " + adoptSourse);
    }
}
