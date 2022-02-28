package Bertucci_Project4.src.BigInt;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class BigInt {
//attributes
    private int size;
    private String input;
    private ArrayList<Integer> arrayInteger;
    private ArrayList<String> arrayString = new ArrayList<String>();
    private String inputString;
    private Boolean neg;
    private String newString;

//constructors

    public BigInt () {
        size = 0;
        input = "";
        inputString = "";
        neg = false;
        setArrayString("");
    }

    public BigInt(String input) {
        setArrayString(input);
        setArrayInteger(input);
        setString(input);
        setBoolean(input);
        setSize(arrayString);
        size = getSize();
    }
    
//methods

    //get values
    public Boolean getInputBoolean() {
        return neg;
    }
    public ArrayList<String> getArrayString() {
        return arrayString;
    }
    public int getSize() {
        return size;
    }
    public String getInputString() {
        return inputString;
    }
    public String getArrayIndex(int i) {
        return arrayString.get(i);
    }

//set methods:

    //detects if arrayString is negative, removes negative sign if it is, changes bool value
    public void setBoolean(String input) {
        if (input.contains("-")) {
            this.neg = true;
        } else {
            this.neg = false;
        }
    }

    //makes an arrayString out of the input, removing the "-" if there is one
    public void setArrayString(String input) {
        if (input.contains("-")) {
            input = input.substring(1, input.length());
        }
        String[] stringArray = input.split("");
        ArrayList<String> stringArrayList = new ArrayList<String>(Arrays.asList(stringArray));

        this.arrayString = stringArrayList;
    }

    //sets the size of the arrayString to a variable size
    public void setSize(ArrayList<String> ary) {
        this.size = ary.size();
    }

    //makes int[] of the input String
    public void setArrayInteger(String input) {
        for (int i = 0; i < arrayString.size(); i++) {
            int val= Integer.parseInt(arrayString.get(i));
            arrayInteger.add(val);
        }
    }

    //makes a string out of the input
    public void setString(String input) {
        this.inputString = input;
    }

    //makes a string out of an ArrayList
    public String makeString(ArrayList<String> s) { 
        String newString = String.join("", s);
        return newString;
    }



//arithmetic methods
    
    public void subtract(BigInt other){

    }

    public void toString(BigInt other) {

    }
}
