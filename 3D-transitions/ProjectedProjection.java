import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

//import javax.swing.AbstractButton;

    public class ProjectedProjection extends JFrame implements ActionListener {

        //public JTextField input1, input2, input3, input4;
        public JTextField input1, input2, input3, input4, input5, input6;   //User Inputs
        public boolean frameResize = true;                                  //Refresh the GUI, it works I don't know why
        public JScrollPane SHistory;                                        //Used to contain the history
        public JTextArea history;                                           //History
        public JTextField IOTextField;                                      //User Input
        public DrawLine drawLine;                                           //DrawLines, uses Buffered Image
        private ImageIcon scanLine;                                         //Updates the main Image with DrawLine
        public ThirdDimensionTrans trans = new ThirdDimensionTrans();             //Contains arrays and points for transitions



        public ProjectedProjection(){
            //setLayout(new BorderLayout());
            //Layout OutLine

            //Image Drawing
            drawLine = new DrawLine();

            //DrawLine drawLine2 = new DrawLine(1,1,1, 1);
            scanLine = new ImageIcon(drawLine);

            //add the icon image to the pane
            add(new JLabel(scanLine), BorderLayout.CENTER);
            JLabel blank = new JLabel("Kouhako");


            //Add the Gridbag to the east panel
            JPanel eastPane = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints(); //Does order matter
            gbc.anchor = GridBagConstraints.NORTHWEST;
            add(eastPane, BorderLayout.EAST); //Add to JFrame

            //Add some spacing
            gbc.insets = new Insets(5,2,0,5);


            //Line 1:
            JLabel inputLabel = new JLabel("Inputs:   ");
            gbc.gridx = 0; gbc.gridy = 0;
            eastPane.add(inputLabel,gbc);

            //Text fields
            int cols = 5;
            input1 = new JTextField(cols);
            gbc.gridx++; gbc.gridy = 0;
            eastPane.add(input1,gbc);

            input2 = new JTextField(cols);
            gbc.gridx++; gbc.gridy = 0;
            eastPane.add(input2,gbc);

            input3 = new JTextField(cols);
            gbc.gridx++; gbc.gridy = 0;
            eastPane.add(input3,gbc);

            input4 = new JTextField(cols);
            gbc.gridx++; gbc.gridy = 0;
            eastPane.add(input4,gbc);

            input5 = new JTextField(cols);
            gbc.gridx++; gbc.gridy = 0;
            eastPane.add(input5,gbc);

            input6 = new JTextField(cols);
            gbc.gridx++; gbc.gridy = 0;
            eastPane.add(input6,gbc);
            //Line 2:
            JButton BDrawLine, BTranslate, BScale, BScaleAt, BRotate, BRotateAt;

            //Align
            gbc.gridx=0; gbc.gridy++;
            eastPane.add(blank,gbc);


            //DrawLine
            BDrawLine = new JButton("Add Line");
            gbc.gridx++;
            eastPane.add(BDrawLine,gbc);
            BDrawLine.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            //Read from file, display line
                            if(input1.getText().equals("")&&input2.getText().equals("")&&input3.getText().equals("")&&input4.getText().equals("")&&trans.getPointsArray().length>0){
                                DrawLine drawLine = new DrawLine(trans.getTwoDimension());
                                scanLine.setImage(drawLine);
                                resize();
                            }
                            else {

                                //Catch miss information
                                try {
                                    //Purpose: take input1, input2, input3, input4 assign respectively to x1, x2, y1, y2
                                    double x1 = Double.parseDouble(input1.getText());
                                    double x2 = Double.parseDouble(input2.getText());
                                    double y1 = Double.parseDouble(input3.getText());
                                    double y2 = Double.parseDouble(input4.getText());
                                    double z1 = Double.parseDouble(input5.getText());
                                    double z2 = Double.parseDouble(input6.getText());

                                    //Then store values into BasicTransitions array
                                    trans.addPoint(x1, x2, y1, y2, z1, z2);

                                    //Update scanLine with new drawing
                                    DrawLine drawLine = new DrawLine(trans.getTwoDimension());
                                    scanLine.setImage(drawLine);

                                    history.append("Line Added: (" + x1 + ", " + y1 + ", " + z1 + ") to (" + x2 + ", " + y2 + ", " + z2 +")\n\n");

                                    clearResults();
                                    resize();

                                } catch (NumberFormatException i) {
                                    history.append("Line Invalid\n");

                                    //Clear Results
                                    clearResults();
                                }
                            }
                        }
                    }
            );


            //Translate
            BTranslate = new JButton("Translate");
            gbc.gridx++;
            eastPane.add(BTranslate,gbc);
            BTranslate.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                //Purpose: take input1, input2, input3, input4 assign respectively to x1, x2, y1, y2
                                double tx = Double.parseDouble(input1.getText());
                                double ty = Double.parseDouble(input2.getText());
                                double tz = Double.parseDouble(input3.getText());
                                trans.basicTranslate(tx, ty, tz);
                                history.append("   Basic Translation Matrix Added: \n" +
                                        "\t|1.0, 0.0, 0.0, 0.0|\n" +
                                        "\t|0.0, 1.0, 0.0, 0.0|\n" +
                                        "\t|0.0, 0.0, 1.0, 0.0|\n" +
                                        "\t|" + tx + ", " + ty + ", " + tz + "1.0|\n\n");
                                clearResults();
                            }catch (NumberFormatException i){
                                history.append("Translate Error\n");

                                //Clear Results
                                clearResults();
                            }
                        }
                    }
            );


            //Scale
            BScale = new JButton("Scale");
            gbc.gridx++;
            eastPane.add(BScale,gbc);
            BScale.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        //Purpose: take input1, input2, input3, input4 assign respectively to x1, x2, y1, y2
                        double Sx = Double.parseDouble(input1.getText());
                        double Sy = Double.parseDouble(input2.getText());
                        double Sz = Double.parseDouble(input3.getText());
                        //Add matrix
                        trans.basicScale(Sx,Sy,Sz);

                        history.append("   Basic Scale Matrix Added: [ \n" +
                                "\t|"+ Sx +", 0.0, 0.0, 0.0|\n" +
                                "\t|0.0"+ Sy +" 0.0, 0.0|\n" +
                                "\t|0.0, 0.0"+ Sz +" 0.0|\n" +
                                "\t|0.0, 0.0, 0.0, 1.0|\n\n");
                        clearResults();
                    }catch (NumberFormatException i){
                        history.append("Basic Scale Error\n");
                       clearResults();
                    }
                }
            });


            //Scale At
            BScaleAt = new JButton("Scale At");
            gbc.gridx++;
            eastPane.add(BScaleAt,gbc);
            BScaleAt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        //Purpose: take input1, input2, input3, input4 assign respectively to x1, x2, y1, y2
                        double Sx = Double.parseDouble(input1.getText());
                        double Sy = Double.parseDouble(input2.getText());
                        double Sz = Double.parseDouble(input3.getText());
                        double Cx = Double.parseDouble(input4.getText());
                        double Cy = Double.parseDouble(input5.getText());
                        double Cz = Double.parseDouble(input6.getText());

                        //Add matrix
                        trans.Scale(Sx, Sy, Sz, Cx, Cy, Cz);
                        history.append("   Basic Translation (Scale At) Matrix: Still needs configuring\n");
                        history.append("   Basic Scale At Matrix Added: Still needs configuring \n");
                        history.append("   Basic Translation (Scale At) Matrix: Still needs configuring\n\n");
                        clearResults();
                    } catch (NumberFormatException i) {
                        history.append("Scale At Error\n");

                        //Clear Results
                        clearResults();
                    }
                }
            });


            //Rotate
            BRotate = new JButton("Rotate");
            gbc.gridx++;
            eastPane.add(BRotate,gbc);

            BRotate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        //Purpose: take input1, input2, input3, input4 assign respectively to x1, x2, y1, y2
                        double angle = Double.parseDouble(input1.getText());
                        String axis = input2.getText().trim();
                        //Add matrix
                        trans.BasicRotate(angle,axis);
                        history.append("Basic Rotate Applied. \n\n");
                        clearResults();
                    }catch (NumberFormatException i){
                        history.append("Basic Rotate Error\n");

                        //Clear Results
                        clearResults();
                    }
                }
            });


            //Rotate At
            BRotateAt = new JButton("Rotate At");
            gbc.gridx++;
            eastPane.add(BRotateAt,gbc);
            BRotateAt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try{
                        //Purpose: take input1, input2, input3, input4 assign respectively to x1, x2, y1, y2
                        double angle = Double.parseDouble(input1.getText());
                        double Cx = Double.parseDouble(input2.getText());
                        double Cy = Double.parseDouble(input3.getText());
                        //Add matrix
                        trans.Rotate(angle,Cx,Cy);
                        history.append("   Basic Translation (Rotate At) Matrix: [1.0, 0.0, 0.0, 0.0, 1.0, 0.0, "+ -Cx +", " + -Cy + ", 1.0]\n");
                        history.append("   Basic Translation (Rotate At) Matrix: " +
                                "["+ Math.toRadians(Math.cos(angle)) + ", "+ Math.toRadians(-Math.sin(angle))+", 0.0, " + Math.toRadians(Math.sin(angle)) +", "+Math.toRadians(Math.cos(angle))+", 0.0, " +"0.0, 0.0, 1.0]\n");
                        history.append("   Basic Translation (Rotate At) Matrix: [1.0, 0.0, 0.0, 0.0, 1.0, 0.0, "+ Cx +", " + Cy + ", 1.0]\n\n");
                        clearResults();
                    }catch (NumberFormatException i){
                        history.append("Rotate At Error\n");

                        //Clear Results
                        clearResults();
                    }
                }
            });

            //Labels
            JLabel blank1 = new JLabel("");
            gbc.gridx = 0; gbc.gridy=8;
            eastPane.add(blank1,gbc);

            JLabel LHistory = new JLabel("Matrix Log");
            gbc.gridx = 0; gbc.gridy=8;
            eastPane.add(LHistory,gbc);


            //History: Contains all the all transitions / clears
            history = new JTextArea(1,1);//100,30);
            history.append("//This is the history were it contains all the transitions. \n"
                    + "//To use this application, type inputs into \"input\" textfields and click the required transition\n"+
                    "//File Name textfield can be used for inputting from or ouputting to files.\n\n");
            history.setWrapStyleWord(true);
            history.setEditable(false);
            SHistory = new JScrollPane(history);
            SHistory.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            SHistory.setPreferredSize(new Dimension(200,250));
            history.setLineWrap(true);

            gbc.gridx = 1; gbc.gridy=8; gbc.gridheight = 5;
            gbc.fill=GridBagConstraints.BOTH;
            gbc.gridwidth = 5;
            eastPane.add(SHistory,gbc);

            //Bolega


            gbc.fill=GridBagConstraints.HORIZONTAL;
            gbc.gridwidth = 1;
            gbc.gridy=11; gbc.gridx = 0; gbc.weighty = 2;


            JLabel kohaku = new JLabel("");
            JLabel johaku = new JLabel("");
            JLabel mohaku = new JLabel("");
            gbc.gridy++;
            eastPane.add(kohaku,gbc);
            gbc.gridy++;
            eastPane.add(johaku,gbc);
            gbc.gridy++;
            eastPane.add(mohaku,gbc);


            JButton clearLine, clearTrans, clearAll;
            clearLine = new JButton("Clear Line");
            clearTrans = new JButton("Clear Trans");
            clearAll = new JButton("Clear All");

            //Clear all lines
            clearLine.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    //Clear the lines
                    trans.clearLines();
                    history.append("Lines have been cleared\n");
                    DrawLine drawLine = new DrawLine(trans.getPointsArray());
                    scanLine.setImage(drawLine);

                    //I don't know why but resizing displays the image
                    if(frameResize) {
                        setSize(getWidth() + 1, getHeight() + 1);
                        frameResize = !frameResize;
                    }else{
                        setSize(getWidth() - 1, getHeight() - 1);
                        frameResize = !frameResize;
                    }
                }
            });

            //Adding functionality to clearTrans
            clearTrans.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Clear the lines
                    trans.clearTransitions();
                    history.append("Transitions have been cleared\n");
                    //scanLine.setImage(null);
                    DrawLine drawLine = new DrawLine(trans.getTwoDimension());
                    scanLine.setImage(drawLine);

                    //I don't know why but resizing displays the image
                    resize();
                }
            });

            //Clear all lines and Transitions
            clearAll.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Clear the lines
                    trans.clearTransitions();
                    trans.clearLines();
                    history.append("Transitions and Lines have been cleared\n");
                    DrawLine drawLine = new DrawLine(trans.getPointsArray());
                    scanLine.setImage(drawLine);

                    //I don't know why but resizing displays the image
                    resize();
                }
            });
            gbc.gridx +=1;
            eastPane.add(clearLine,gbc);
            gbc.gridx +=1;
            eastPane.add(clearTrans,gbc);
            gbc.gridx+=1;
            eastPane.add(clearAll,gbc);

            //gbc.gridy+= 2; //15
            System.out.print(gbc.gridy);
            gbc.gridx +=1;
            gbc.weighty = 3;
            JButton applyButton;
            applyButton = new JButton("Apply Trans.");
            eastPane.add(applyButton,gbc);
            applyButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //Purpose: take input1, input2, input3, input4 assign respectively to x1, x2, y1, y2

                            trans.applyTransitions();
                            //trans.applyViewingTransformations();

                            history.append("Transitions Applied \n");
                            double[][] temp = trans.getTwoDimension();

                            //String for all transition added to the history
                            String transition = "";
                            for(int x = 0; x<temp.length; x++){
                                transition = transition + x + ": " + "[ ";
                                for (int y = 0; y < temp[0].length; y++){
                                    transition = transition + temp[x][y] +", ";
                                }
                                transition = transition + "] \n";
                            }
                            history.append(transition);
                            history.append("\n");
                            //Draw Lines and apply them
                            DrawLine drawLine = new DrawLine(trans.getTwoDimension());
                            scanLine.setImage(drawLine);

                            //I don't know why but resizing displays the image
                            resize();
                        }

                    }
            );



            //Spacers
            JLabel dopehaku = new JLabel("Update Image: ");
            JLabel showemhaku = new JLabel("");
            JLabel doubtmehaku = new JLabel("");
            JLabel mehaku = new JLabel("");
            JLabel DEEzHaku = new JLabel("");
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.weighty = 1;
            eastPane.add(dopehaku,gbc);
            gbc.gridy++;
            eastPane.add(showemhaku,gbc);
            gbc.gridy++;
            eastPane.add(doubtmehaku,gbc);
            gbc.gridy++;
            eastPane.add(mehaku,gbc);
            gbc.gridy++;
            eastPane.add(DEEzHaku,gbc);

            //FileName LAbel
            gbc.gridy++; gbc.gridx = 0;
            JLabel FileName = new JLabel("File Name:");
            eastPane.add(FileName,gbc);

            //For IOTextField
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridwidth = 2;
            gbc.gridx = 1;
            IOTextField = new JTextField();
            eastPane.add(IOTextField,gbc);

            //For BinputFile
            gbc.gridwidth = 2;
            gbc.gridx = 3;
            JButton BinputFile = new JButton("Input from File");
            eastPane.add(BinputFile,gbc);

            //Input from File
            BinputFile.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            File file = new File(IOTextField.getText());
                            double x1, x2, y1, y2, z1 ,z2;
                            String temp;
                            try {
                                String lines, number;
                                Scanner in = new Scanner(new FileReader(file));
                                history.append("Reading from: " + file.getName() +"\n");

                                try{
                                    while((lines=in.nextLine())!=null){
                                        history.append(" Reading: "+ lines + "\n");
                                        try {
                                            x1 = Double.parseDouble(lines.substring(0, lines.indexOf(" ")));
                                            lines = lines.substring(lines.indexOf(" ")+1, lines.length());
                                            x2 = Double.parseDouble(lines.substring(0, lines.indexOf(" ")));
                                            lines = lines.substring(lines.indexOf(" ")+1, lines.length());
                                            y1 = Double.parseDouble(lines.substring(0, lines.indexOf(" ")));
                                            lines = lines.substring(lines.indexOf(" ")+1, lines.length());
                                            y2 = Double.parseDouble(lines.substring(0, lines.indexOf(" ")));
                                            lines = lines.substring(lines.indexOf(" ")+1, lines.length());
                                            z1 = Double.parseDouble(lines.substring(0, lines.indexOf(" ")));
                                            lines = lines.substring(lines.indexOf(" ")+1, lines.length());
                                            z2 = Double.parseDouble(lines.substring(0, lines.length()));
                                            trans.addPoint(x1,x2,y1,y2,z1,z2);
                                            history.append("  Line Added: (" + x1 + ", " + y1 + ", "+ z1 + ") (" + x2 + ", " + y2 +", " +z2+")\n");
                                        } catch (NumberFormatException i){
                                            history.append("  Wrong Format: Each line should contain x1, x2, y1 ,y2 \n" +
                                                    "Continuing \n\n");
                                        }
                                    }
                                }catch(NoSuchElementException i){}


                            } catch(FileNotFoundException i){
                                history.append("File not found\n");
                            }


                        }
                    }
            );

            gbc.gridx = 5;
            JButton BOutputFile = new JButton("Output to File");
            eastPane.add(BOutputFile,gbc);
            BOutputFile.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            BufferedWriter bw = null;
                            FileWriter fw = null;

                            String stringPoints =""; //String representation of the coordinate points

                            //Code Format obtained from: https://www.mkyong.com/java/how-to-write-to-file-in-java-bufferedwriter-example/
                            for(int i =0; i<trans.getPointsArray().length; i++) {
                                for (int x = 0; x < trans.getPointsArray()[i].length; x++) {
                                    stringPoints += trans.getPointsArray()[i][x] + " ";
                                }
                                stringPoints += "\n";
                            }
                            try{
                                fw = new FileWriter(IOTextField.getText());
                                bw = new BufferedWriter(fw);
                                bw.write(stringPoints);
                            }catch(java.io.IOException p){
                                System.out.println("ERROR");
                            }finally{
                                try{

                                    if(bw !=null)
                                        bw.close();

                                    if(fw !=null)
                                        fw.close();

                                }catch (java.io.IOException m){

                                }
                            }
                            history.append("Output to file: " + IOTextField.getText() +"\n"+  stringPoints + "\n");
                        }
                    }
            );

            gbc.gridy+=2;
            gbc.gridx = 0;
//        gbc.weightx = 23;
//        gbc.weighty =24;
            gbc.gridwidth = 1;
            JLabel LLineRead = new JLabel("Line to Read");
            JTextField JLineRead = new JTextField();
            eastPane.add(LLineRead,gbc);
            gbc.gridx = 1;
            eastPane.add(JLineRead,gbc);





            //DO NOT TOUCH
            //DO NOT TOUCH
            //DO NOT TOUCH
            gbc.weightx = 1; gbc.weighty = 200;
            gbc.gridy=200;
            eastPane.add(blank,gbc);


            //.addActionListener(this);
            setSize(1200,750);
            pack();
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setVisible(true);//sets the frame to be visible
        }
        public static void main(String[]Args){

            ProjectedProjection frame = new ProjectedProjection();
        }

        @Override
        public void actionPerformed(ActionEvent evt){
            System.out.println("Nothing happened");
        }



        public void resize() {
            setSize(getWidth() + 1, getHeight() + 1);
            setSize(getWidth() - 1, getHeight() - 1);
        }

        public void clearResults(){
            input1.setText("");
            input2.setText("");
            input3.setText("");
            input4.setText("");
            input5.setText("");
            input6.setText("");
        }
    }


