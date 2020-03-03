package com.coxph;
import java.util.ArrayList;

public class Input {
    public Input() {
        super();
        params = new ArrayList<Float>();
    }

    private ArrayList<Float> params;

    public ArrayList<Float> getParams() {
        return params;
    }

    public void setParams(ArrayList<Float> params) {
        this.params = params;
    }
}
