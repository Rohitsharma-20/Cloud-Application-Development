package org.example;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Scanner;


public class App extends JFrame
{
    public App(){
        displayWelcomeScreen();
    }
    private void displayWelcomeScreen(){
        JFrame root1 =new JFrame();
        root1.setSize(800, 800);
        root1.setVisible(true);

        JPanel panel= new JPanel();
        root1.add(panel);
        JLabel label11=new JLabel(new ImageIcon("/Users/rohitsharma/Stock7.jpg"));
        label11.setBounds(0,0,800,800);
        panel.add(label11);
        panel.setBackground(Color.white);
        //panel.setBorder(BorderFactory.createLineBorder(Color.black));

        JProgressBar progress=new JProgressBar(0, 100);
        progress.setForeground(Color.blue);
        root1.add(BorderLayout.PAGE_END,progress);
        //root1.setLocationRelativeTo(null);
        root1.setVisible(true);

        root1.revalidate();
        timer=new Timer(100,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x=progress.getValue();
                if(x==100){
                    root1.dispose();
                    App.this.setVisible(true);
                    timer.stop();
                }
                else{
                    progress.setValue(x+2);
                }
            }
        });
        timer.start();
        //root1.setVisible(false);

    }
    Timer timer;
    public static String getData(String Stock)throws Exception {
        StringBuffer br=new StringBuffer();

        br.append("<html>" +
                "<body style='text-align:left;color:black;'>");
        String url="https://www.investing.com/equities/"+Stock;
        Document doc=Jsoup.connect(url).get();

        //Name of Stock
        br.append(Stock).append("<br>").append("<br>");

        //Industry Name
        /*Elements elements=doc.select("./html/body/section[1]/section[1]/div/div/div[3]/div/div/text()[1]");
        elements.forEach((e) -> {
            String text = e.select("/html/body/section[1]/section[1]/div/div/div[3]/div/div/text()[1]").text();
            br.append(text).append("<br>");
        });
*/
        //Current Price
        Elements elements=doc.select("span.text-2xl");
        elements.forEach((e) -> {
            String text = e.select("span.text-2xl").text();
                br.append(text).append("<br>").append("<br>");
        });
        //Change
        Elements elements1=doc.select("span.instrument-price_change-value__h13Qh");
        elements1.forEach((e) -> {
            String text = e.select("span.instrument-price_change-value__h13Qh").text();
                br.append(text).append("<br>").append("<br>");
        });

        //Volume
        Elements elements2=doc.select("div.trading-hours_value__5_NnB");
        elements2.forEach((e) -> {
            String text = e.select("div.trading-hours_value__5_NnB").text();
                br.append(text).append("<br>").append("<br>");
        });

        br.append("</body>" +
                "</html>");
        return br.toString();

    }

    public static void main(String[] args) throws InterruptedException {
    /*    Scanner sc=new Scanner(System.in);
        System.out.println("Enter the required Stock:");
        String co=sc.nextLine();
        String.out.println(getData(co));
    */

        // GUI using Java
        JFrame root=new JFrame("StockScraper");
        root.setSize(800, 800);

        // Text-Box
        Font f=new Font("Josefin Sans",Font.PLAIN,20);
        Font f1=new Font("Josefin Sans",Font.PLAIN,15);
        Font f2=new Font("Josefin Sans",Font.ITALIC,18);

        JTextField field=new JTextField();
        JLabel datalabel=new JLabel();


        field.setFont(f);
        field.setHorizontalAlignment(SwingConstants.CENTER);

        // Defining Fields
        JLabel label=new JLabel("Enter the Name of Stock:");
        JButton button=new JButton("Search");
        JButton button7=new JButton("Compare");
        JLabel label1=new JLabel("Trending:");
        JLabel label2=new JLabel("Compare:");
        JLabel label3=new JLabel(new ImageIcon("/Users/rohitsharma/Stock6.jpg"));
        //JLabel label10=new JLabel(new ImageIcon("/Users/rohitsharma/Rectangle.png"));
        JLabel label4=new JLabel("VS");
        JLabel label5=new JLabel("Company Name                  :");
        JLabel label6=new JLabel("Current Price                       :");
        JLabel label7=new JLabel("Change(%)                           :");
        JLabel label8=new JLabel("Volume                               :");
        JLabel label9=new JLabel("Bid/Ask                              :");
        JLabel label10=new JLabel("Day Range                          :");
        JButton button2=new JButton("reliance-industries");
        JButton button3=new JButton("hdfc-bank-ltd");
        JButton button4=new JButton("asian-paints");
        JButton button5=new JButton("adani-enterprises");
        JButton button6=new JButton("infosys");
        JTextField field1=new JTextField("Stock I");
        JTextField field2=new JTextField("Stock II");
        String[] stck = {"Other Stocks", "Airtnt", "Drunk-Skunks-DC", "Dynex", "X-MASK-Coin", "ExodusExt", "XRP-Classic", "Hivemapper", "FNCY", "Poke-Play", "NUGEN-COIN", "SpillWays", "Hooked-Protocol"};
        JComboBox dropdown=new JComboBox(stck);
        //JLabel datalabel1=new JLabel();
        JButton button8=new JButton("Search");
        JButton button9=new JButton("Clear");
        JLabel datalabel2=new JLabel();

        field1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e1) {
                if (field1.getText().equals("Stock I")) {
                    field1.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e1) {
                if (field1.getText().equals("")) {
                    field1.setText("Stock I");
                }
            }
        });

        field2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e1) {
                if (field2.getText().equals("Stock II")) {
                    field2.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e1) {
                if (field2.getText().equals("")) {
                    field2.setText("Stock II");
                }
            }
        });

        field1.setFont(f2);
        field2.setFont(f2);
        field1.setForeground(Color.GRAY);
        field2.setForeground(Color.GRAY);
        field1.setHorizontalAlignment(SwingConstants.CENTER);
        field2.setHorizontalAlignment(SwingConstants.CENTER);

        // Location of fields
        datalabel.setBounds(360,370,200,320);
        //datalabel1.setBounds(400,285,175,300);
        datalabel2.setBounds(585,370,200,320);
        field.setBounds(290,10,340,50);
        field1.setBounds(170,170,200,50);
        field2.setBounds(430,170,200,50);
        label.setBounds(50,10,300,50);
        button.setBounds(650,10,100,50);
        button7.setBounds(650,170,100,50);
        label3.setBounds(0,0,800,800);
        label1.setBounds(50,90,100,50);
        label2.setBounds(50,170,100,50);
        label4.setBounds(387,170,100,50);
        label5.setBounds(75,360,300,50);
        label6.setBounds(75,410,300,50);
        label7.setBounds(75,460,300,50);
        label8.setBounds(75,510,300,50);
        label9.setBounds(75,560,300,50);
        label10.setBounds(75,610,300,50);
        //label10.setBounds(540,320,2,200);
        button2.setBounds(170,90,110, 50);
        button3.setBounds(290,90,110, 50);
        button4.setBounds(410,90,110, 50);
        button5.setBounds(530,90,110, 50);
        button6.setBounds(650,90,110,50);
        dropdown.setBounds(410,250,220,50);
        button8.setBounds(650,250,100,50);
        button9.setBounds(650,700,100,50);



        //Action Listeners for Buttons

        button9.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                field1.setText("");
                field.setText("");
                field2.setText("");
                datalabel.setText("");
                datalabel2.setText("");

            }
        });

        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maindata = (String) dropdown.getItemAt(dropdown.getSelectedIndex());
                String result = null;
                try {
                    result = getData(maindata);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                datalabel.setText(result);
            }
        });

        button2.addActionListener((event)->{
            try {
                String maindata = "HDFC";
                String result = getData(maindata);
                datalabel.setText(result);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });

        button3.addActionListener((event)->{
            try {
                String maindata = "Mahindra & Mahindra";
                String result = getData(maindata);
                datalabel.setText(result);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });

        button4.addActionListener((event)->{
            try {
                String maindata = "Reliance";
                String result = getData(maindata);
                datalabel.setText(result);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });

        button5.addActionListener((event)->{
            try {
                String maindata = "Tata Motors";
                String result = getData(maindata);
                datalabel.setText(result);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });

        button6.addActionListener((event)->{
            try {
                String maindata = "Infosys";
                String result = getData(maindata);
                datalabel.setText(result);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });

        button.addActionListener((event)->{
            try {
                String maindata = field.getText();
                String result = getData(maindata);
                datalabel.setText(result);
            }
            catch (Exception e)
            {
                datalabel.setText("Invalid Stock!");
                e.printStackTrace();
            }
        });

        button7.addActionListener((event)->{
            try {
                String maindata = field1.getText();
                String result = getData(maindata);
                datalabel.setText(result);
            }
            catch (Exception e)
            {
                datalabel2.setText("Invalid Stock!");
                e.printStackTrace();
            }
        });

        button7.addActionListener((event)->{
            try {
                String maindata = field2.getText();
                String result = getData(maindata);
                datalabel2.setText(result);
            }
            catch (Exception e)
            {
                datalabel.setText("Invalid Stock!");
                e.printStackTrace();
            }
        });


        // Set Font
        button.setFont(f);
        button7.setFont(f);
        label.setFont(f);
        datalabel.setFont(f);
        //datalabel1.setFont(f);
        datalabel2.setFont(f);
        label1.setFont(f);
        label2.setFont(f);
        label4.setFont(f);
        label5.setFont(f);
        label6.setFont(f);
        label7.setFont(f);
        label8.setFont(f);
        label9.setFont(f);
        label10.setFont(f);
        button2.setFont(f);
        button3.setFont(f);
        button4.setFont(f);
        button5.setFont(f);
        button6.setFont(f);
        button8.setFont(f);
        button9.setFont(f);
        dropdown.setFont(f1);


        label.setForeground(Color.WHITE);
        label1.setForeground(Color.WHITE);
        label2.setForeground(Color.WHITE);
        label4.setForeground(Color.WHITE);

        //Root Setup = Web Scraper of Stocks
        root.add(field);
        root.add(field1);
        root.add(field2);
        root.add(datalabel);
        //root.add(datalabel1);
        root.add(datalabel2);
        root.add(label);
        root.add(button);
        root.add(label1);
        root.add(label2);
        root.add(label4);
        root.add(label5);
        root.add(label6);
        root.add(label7);
        root.add(label8);
        root.add(label9);
        root.add(label10);
        root.add(button2);
        root.add(button3);
        root.add(button4);
        root.add(button5);
        root.add(button6);
        root.add(button7);
        root.add(button8);
        root.add(dropdown);
        root.add(button9);
        //root.add(label10);
        root.add(label3);
        //root.setLocationRelativeTo(null);
        new App();
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        root.setVisible(true);

        // Check-up
        System.out.println("Successful!");
        //new App();
    }
}