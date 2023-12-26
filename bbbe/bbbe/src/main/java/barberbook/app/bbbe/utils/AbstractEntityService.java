package barberbook.app.bbbe.utils;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEntityService<T> {


    public abstract String generateId(T e);

    public List<Character> findConsonanti(String text){
        text = text.toUpperCase();
        ArrayList<Character> consonanti = new ArrayList<>();
        for(char c : text.toCharArray()){
            if(!isVocale(c)){
                consonanti.add(c);
            }
        }
        return consonanti;
    }

    public List<Character> findVocali(String text){
        text = text.toUpperCase();
        ArrayList<Character> vocali = new ArrayList<>();
        for(char c : text.toCharArray()){
            if(isVocale(c)){
                vocali.add(c);
            }
        }
        return vocali;
    }

    protected boolean isVocale(char c){
        return (c=='A'||c=='E'||c=='I'||c=='O'||c=='U');
    }

}
