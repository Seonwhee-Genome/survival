package com.coxph;

import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CHD_Risk {
    public double Age_factor(int age, int sex) {
        double[] age_factor = new double[2];
        age_factor[0] = 0.13759*(age-45.7991);
        age_factor[1] = 0.12962*(age-45.5808);
        return age_factor[sex-1];
    }

    public double Age_Square(int age, int sex) {

        double[] age_factor = new double[2];
        age_factor[0] = -0.0006964*(age*age-2186.58);
        age_factor[1] = -0.0003965*(age*age-2363.65);
        return age_factor[sex-1];
    }


    public double Smoking_factor(int smoker, int sex) {
        double[] smoking_factor = new double[2];
        if (smoker == 1) {
            smoking_factor[0] = 0.0;
            smoking_factor[1] = 0.0;
        }
        else if (smoker == 2) {
            smoking_factor[0] = -0.00207*(1-0.23029);
            smoking_factor[1] = 0.23099*(1-0.03970);
        }
        else if (smoker == 3) {
            smoking_factor[0] = 0.60138*(1-0.53016);
            smoking_factor[1] = 0.67653*(1-0.05079);
        }
        return smoking_factor[sex-1];
    }


    public double Total_Chol(double tc, int sex) {
        double[] tc_factor = new double[2];
        if (tc < 200 && tc >=160) {
            tc_factor[0] = 0.30303*(Math.log(tc)-0.43540);
            tc_factor[1] = 0.20005*(Math.log(tc)-0.41642);

        }
        else if (tc <240 && tc >= 200) {
            tc_factor[0] = 0.72508*(Math.log(tc)-0.31439);
            tc_factor[1] = 0.44176*(Math.log(tc)-0.29841);
        }
        else if (tc <280 && tc >= 240) {
            tc_factor[0] = 1.02770*(Math.log(tc)-0.08486);
            tc_factor[1] = 0.52267*(Math.log(tc)-0.09640);
        }
        else if (tc >= 280) {
            tc_factor[0] = 1.51018*(Math.log(tc)-0.01387);
            tc_factor[1] = 1.035735*(Math.log(tc)-0.02196);
        }
        else if (tc < 160) {
            tc_factor[0] = 0.0;
            tc_factor[1] = 0.0;
        }
        return tc_factor[sex-1];
    }


    public double HDL_factor(double hdl, int sex) {
        double[] hdl_factor = new double[2];
        if (hdl < 45 && hdl >= 35) {
            hdl_factor[0] = -0.41580*(Math.log(hdl)-0.31063);
            hdl_factor[1] = -0.28121*(Math.log(hdl)-0.18651);
        }
        else if (hdl < 50 && hdl >= 45) {
            hdl_factor[0] = -0.59809*(Math.log(hdl)-0.22692);
            hdl_factor[1] = -0.18543*(Math.log(hdl)-0.16015);
        }
        else if (hdl < 60 && hdl >= 50) {
            hdl_factor[0] = -0.80256*(Math.log(hdl)-0.27050);
            hdl_factor[1] = -0.47018*(Math.log(hdl)-0.30597);
        }
        else if (hdl >= 60) {
            hdl_factor[0] = -1.13973*(Math.log(hdl)-0.11410);
            hdl_factor[1] = -0.72046*(Math.log(hdl)-0.31451);
        }
        else if (hdl < 35) {
            hdl_factor[0] = 0.0;
            hdl_factor[1] = 0.0;
        }
        return hdl_factor[sex-1];
    }


    public double DM_factor(int diabetes, int sex) {
        double dm_factor[] = new double[2];
        if (diabetes == 1) {
            dm_factor[0] = 0.49443*(1-0.08389);
            dm_factor[1] = 0.58729*(1-0.06026);
        }
        else if (diabetes == 0) {
            dm_factor[0] = 0.0;
            dm_factor[1] = 0.0;
        }
        return dm_factor[sex-1];
    }


    public double HT_factor(double sys, int sex) {
        double ht_factor[] = new double[2];
        if (sys < 140 && sys >= 120) {
            ht_factor[0] = 0.24130*(Math.log(sys)-0.40678);
            ht_factor[1] = 0.41491*(Math.log(sys)-0.32308);
        }
        else if (sys < 160 && sys >= 140) {
            ht_factor[0] = 0.54176*(Math.log(sys)-0.18005);
            ht_factor[1] = 0.66187*(Math.log(sys)-0.14102);
        }
        else if (sys >= 160) {
            ht_factor[0] = 0.79091*(Math.log(sys)-0.06823);
            ht_factor[1] = 1.10282*(Math.log(sys)-0.06657);
        }
        else if (sys < 120) {
            ht_factor[0] = 0.0;
            ht_factor[1] = 0.0;
        }
        return ht_factor[sex-1];
    }


    public double KRS(double exp_sum, int sex) {
        double[] surv = {0.99313, 0.99815};
        double krs = 1 - Math.pow(surv[sex-1], exp_sum);
        return krs;
    }


    public static void main( String[] args )
    {
        long start = System.currentTimeMillis();
        double AGE, AGESQ, SMOKING, HDL, TOT_CHOL, DM, HTN, x, y, risk_score;

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

        CHD_Risk KRS = new CHD_Risk();
        float age = inList.get(0);
        float sex = inList.get(1);
        float smoking = inList.get(2);
        float hdl = inList.get(3);
        float tot_chol = inList.get(4);
        float dm = inList.get(5);
        float htn = inList.get(6);

        AGE = KRS.Age_factor((int)age, (int)sex);
        AGESQ = KRS.Age_Square((int)age, (int)sex);
        SMOKING = KRS.Smoking_factor((int)smoking, (int)sex);
        HDL = KRS.HDL_factor((double)hdl, (int)sex);
        TOT_CHOL = KRS.Total_Chol((double)tot_chol, (int)sex);
        DM = KRS.DM_factor((int)dm, (int)sex);
        HTN = KRS.HT_factor((double)htn,(int)sex);

        x = AGE + AGESQ + SMOKING + HDL + TOT_CHOL + DM + HTN;
        y = Math.exp(x);

        risk_score = KRS.KRS(y, (int)sex);
        long end = System.currentTimeMillis();

        System.out.println("The risk of CHD : " + risk_score);
        System.out.println("Elapsed Time : " + (end - start) + "ms");

    }


}
