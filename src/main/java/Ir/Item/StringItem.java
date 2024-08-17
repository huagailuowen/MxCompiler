package Ir.Item;

import Ir.Type.IRBaseType;

@lombok.Getter
@lombok.Setter
public class StringItem extends Item{
    String value;
    public StringItem(String name, String value) {
        super(null, name);
        this.value = value;
    }
    @Override
    public String toString() {
        return name+" = private unnamed_addr constant ["+(value.length()+1)+" x i8] c\""+convert(value)+"\\00\"";
    }
    @Override
    public String globalDef() {
        return toString();
    }
    public static String convert(String s){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='\n'){
                sb.append("\\0A");
            }else if(c=='\t'){
                sb.append("\\09");
            }else if(c=='\r'){
                sb.append("\\0D");
            }else if(c=='\"'){
                sb.append("\\22");
            }else if(c=='\\'){
                sb.append("\\5C");
            }else if(c=='\0'){
                sb.append("\\00");
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
