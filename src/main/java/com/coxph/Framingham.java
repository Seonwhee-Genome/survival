package com.coxph;

import java.util.HashMap;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Framingham {
    public int Age_factor(int age, int sex) {
        int[] point = new int[2];

        if (age >= 30 && age <= 34) {
            point[0] = -1;
            point[1] = -9;
        }
        else if (age >= 35 && age <= 39) {
            point[0] = 0;
            point[1] = -4;
        }
        else if (age >= 40 && age <= 44) {
            point[0] = 1;
            point[1] = 0;
        }
        else if (age >= 45 && age <= 49) {
            point[0] = 2;
            point[1] = 3;
        }
        else if (age >= 50 && age <= 54) {
            point[0] = 3;
            point[1] = 6;
        }
        else if (age >= 55 && age <= 59) {
            point[0] = 4;
            point[1] = 7;
        }
        else if (age >= 60 && age <= 64) {
            point[0] = 5;
            point[1] = 8;
        }
        else if (age >= 65 && age <= 69) {
            point[0] = 6;
            point[1] = 8;
        }
        else if (age >= 70 && age <= 74) {
            point[0] = 7;
            point[1] = 8;
        }
        else {
            point[0] = 0;
            point[1] = 0;
        }

        return point[sex-1];
    }

    public int Smoking_factor(int smoker) {
        int point = 0;
        if (smoker == 1) {
            point = 0;
        }
        else if (smoker == 2) {
            point = 0;
        }
        else if (smoker == 3) {
            point = 2;
        }
        return point;
    }


    public int Total_Chol(double tc, int sex) {
        int[] point = new int[2];
        if (tc < 200 && tc >=160) {
            point[0] = 0;
            point[1] = 0;
        }
        else if (tc <240 && tc >= 200) {
            point[0] = 1;
            point[1] = 1;
        }
        else if (tc <280 && tc >= 240) {
            point[0] = 2;
            point[1] = 1;
        }
        else if (tc >= 280) {
            point[0] = 3;
            point[1] = 3;
        }
        else if (tc < 160) {
            point[0] = -3;
            point[1] = -2;
        }

        return point[sex-1];

    }


    public int HDL_factor(double hdl, int sex) {
        int[] point = new int[2];
        if (hdl < 45 && hdl >= 35) {
            point[0] = 1;
            point[1] = 2;
        }
        else if (hdl < 50 && hdl >= 45) {
            point[0] = 0;
            point[1] = 1;
        }
        else if (hdl < 60 && hdl >= 50) {
            point[0] = 0;
            point[1] = 0;
        }
        else if (hdl >= 60) {
            point[0] = -2;
            point[1] = -3;
        }
        else if (hdl < 35) {
            point[0] = 2;
            point[1] = 5;
        }
        return point[sex-1];
    }


    public int DM_factor(int diabetes, int sex) {
        int[] point = new int[2];
        if (diabetes == 1) {
            point[0] = 0;
            point[1] = 0;
        }
        else if (diabetes == 0) {
            point[0] = 2;
            point[1] = 4;
        }

        return point[sex-1];

    }


    public int HT_factor(double sys, int sex) {
        int[] point = new int[2];
        if (sys < 130 && sys >= 120) {
            point[0] = 0;
            point[1] = 0;
        }
        else if (sys < 140 && sys >= 130) {
            point[0] = 1;
            point[1] = 0;
        }
        else if (sys < 160 && sys >= 140) {
            point[0] = 2;
            point[1] = 2;
        }
        else if (sys >= 160) {
            point[0] = 3;
            point[1] = 3;
        }
        else if (sys < 120) {
            point[0] = 0;
            point[1] = -3;
        }

        return point[sex-1];

    }


    public double FRS(int total_pt, int sex) {
        double frs = 0.0;
        HashMap men_table = new HashMap();
        HashMap women_table = new HashMap();
        men_table.put(-1, new Double(0.02));
        men_table.put(0, new Double(0.03));
        men_table.put(1, new Double(0.03));
        men_table.put(2, new Double(0.04));
        men_table.put(3, new Double(0.05));
        men_table.put(4, new Double(0.07));
        men_table.put(5, new Double(0.08));
        men_table.put(6, new Double(0.1));
        men_table.put(7, new Double(0.13));
        men_table.put(8, new Double(0.16));
        men_table.put(9, new Double(0.2));
        men_table.put(10, new Double(0.25));
        men_table.put(11, new Double(0.31));
        men_table.put(12, new Double(0.37));
        men_table.put(13, new Double(0.45));
        men_table.put(14, new Double(0.53));
        women_table.put(-2, new Double(0.01));
        women_table.put(-1, new Double(0.02));
        women_table.put(0, new Double(0.02));
        women_table.put(1, new Double(0.02));
        women_table.put(2, new Double(0.03));
        women_table.put(3, new Double(0.03));
        women_table.put(4, new Double(0.04));
        women_table.put(5, new Double(0.04));
        women_table.put(6, new Double(0.05));
        women_table.put(7, new Double(0.06));
        women_table.put(8, new Double(0.07));
        women_table.put(9, new Double(0.08));
        women_table.put(10, new Double(0.1));
        women_table.put(11, new Double(0.11));
        women_table.put(12, new Double(0.13));
        women_table.put(13, new Double(0.15));
        women_table.put(14, new Double(0.18));
        women_table.put(15, new Double(0.2));
        women_table.put(16, new Double(0.24));
        women_table.put(17, new Double(0.27));

        if (sex == 1) {
            if (total_pt > 14) {
                frs = 1.0;
            }
            else if (total_pt < -1) {
                frs = 0.0;
            }
            else {
                frs = (double) men_table.get(total_pt);
            }
        }
        else if (sex == 2) {
            if (total_pt > 17) {
                frs = 1.0;
            }
            else if (total_pt < -2) {
                frs = 0.0;
            }
            else {
                frs = (double) women_table.get(total_pt);
            }
        }
        return frs;
    }


    public static void main(String[] args){
        long start = System.currentTimeMillis();
        int AGE, SMOKING, HDL, TOT_CHOL, DM, HTN, x;
        double risk_score;
        String jsonStr = "{\r\n" +
                " \"params\": [49, 2, 3, 69.566, 226.199556, 0, 172.757878]\r\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();

        //입력변수 초기화
        Input input = null;
        try {
            input = mapper.readValue(jsonStr, Input.class);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        ArrayList<Float> inList = new ArrayList<Float>();
        inList = input.getParams();

        Framingham FRS = new Framingham();
        float age = inList.get(0);
        float sex = inList.get(1);
        float smoking = inList.get(2);
        float hdl = inList.get(3);
        float tot_chol = inList.get(4);
        float dm = inList.get(5);
        float htn = inList.get(6);

        AGE = FRS.Age_factor((int)age, (int)sex);

        SMOKING = FRS.Smoking_factor((int)smoking);
        HDL = FRS.HDL_factor((double)hdl, (int)sex);
        TOT_CHOL = FRS.Total_Chol((double)tot_chol, (int)sex);
        DM = FRS.DM_factor((int)dm, (int)sex);
        HTN = FRS.HT_factor((double)htn,(int)sex);

        x = AGE + SMOKING + HDL + TOT_CHOL + DM + HTN;
        risk_score = FRS.FRS(x, (int)sex);
        long end = System.currentTimeMillis();
        System.out.println("The Risk of ASCVD : " + risk_score);
        System.out.println("Elapsed Time : " + (end - start) + "ms");

    }


}
