package PageReplace;
import java.util.*;

import java.awt.*;
import java.awt.event.*;

public class ClockGUI extends Frame {
	public ClockGUI() {
		Panel pnl = new Panel(); 
		Button btn = new Button();
		pnl.add(btn);
		setVisible(true);
	}
    public static void main(String[] args) {
        ArrayList<ClockBuffer> lstofbuffers = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            lstofbuffers.add(new ClockBuffer(i));
        }
        Clock clk = new Clock(lstofbuffers);
        ClockGUI bleh = new ClockGUI();



    }
    

}