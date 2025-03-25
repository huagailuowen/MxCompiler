package Ir.Type;



import java.util.ArrayList;

@lombok.Setter
@lombok.Getter
public class IRClassType extends IRBaseType {
    ArrayList<IRBaseType> memberVariables;
    public IRClassType(String name) {
        super(name);
        memberVariables = new ArrayList<>();
    }
    public IRClassType(String name, ArrayList<IRBaseType> memberVariables) {
        super(name);
        this.memberVariables = memberVariables;
    }
    public void addMemberVariable(IRBaseType memberVariable) {
        memberVariables.add(memberVariable);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("%class." + name + " = type {");
        for (int i = 0; i < memberVariables.size(); i++) {
            if(memberVariables.get(i).toString().equals("i1")){
                ret.append("i8");
            }else {
                ret.append(memberVariables.get(i).toString());
            }
            if (i != memberVariables.size() - 1) {
                ret.append(", ");
            }
        }
        ret.append("}");
        return ret.toString();
    }




}
