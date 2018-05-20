public class BasicTransitions {
    private double[][] TransitionMatrix = new double[0][0];
    private double [][] pointsArray = new double[0][0];
    public BasicTransitions() {
        //Initialize list with 4 integers containing points

    }
        //For calculations
        public double[][] getTransitionMatrix(){
            return TransitionMatrix;
        }

        //For drawing lines
        public double[][] getPointsArray(){
            return pointsArray;
        }

        //Clear lines
        public void clearLines(){
            pointsArray = new double[0][0];
        }

    public void clearTransitions(){
        TransitionMatrix = new double[0][0];
    }
        //The line is represented as x1, x2, y1, y2 so appropiate coordinate need assignment
        public void applyTransitions(){
            double x1, y1, x2, y2;
            int index = 0;
            double[] point1 = new double[3], point2 = new double[3];
            for (double[] coord: pointsArray) {
                //Line representation to matrix representation;
                    point1 = new double[]{coord[0], coord[2], 1};
                    point2 = new double[]{coord[1], coord[3], 1};
                        System.out.println("Point 1: "+ point1[0] + " "+ point1[1] + " " + point1[2]);
                        System.out.println("Transition Matrix: "
                            + TransitionMatrix[0][0]+ " "
                            + TransitionMatrix[0][1]+ " "
                            + TransitionMatrix[0][2]+ " "
                            + TransitionMatrix[0][3]+ " "
                            + TransitionMatrix[0][4]+ " "
                            + TransitionMatrix[0][5]+ " "
                            + TransitionMatrix[0][6]+ " "
                            + TransitionMatrix[0][7]+ " "
                            + TransitionMatrix[0][8]+ ".");


                //New variables to store original values
                    double newx0, newx1, newy0, newy1;

                //Go through Geometric Transitions
                for (double[] trans : TransitionMatrix) {
                    //Starting point dot product
                        System.out.println("point1[0] before trans: " + point1[0]);
                        System.out.println("point1[1] before trans: " + point1[1]);
                        System.out.println("point2[0] before trans: " + point2[0]);
                        System.out.println("point2[1] before trans: " + point2[1]);
                    //Applying transitions to x0
                        newx0 = (point1[0]*trans[0])+ (point1[1]*trans[3])+ (point1[2]*trans[6]);
                        //Test Logs
                            System.out.println("trans: " +trans[0]+" "+ trans[3]+" "+ trans[6]);
                            System.out.println("trans: Point 1: " +trans[0]*point1[0]+" "+ trans[3]*point1[0]+" "+ trans[6]*point1[0]);

                    //Applying transitions to y0
                        newy0 = point1[0]*trans[1]+ point1[1]*trans[4]+ point1[2]*trans[7];
                        //Test logs
                            System.out.println("trans1: " +trans[1]+" "+ trans[4]+" "+ trans[7]);
                            System.out.println("trans + Point 2: " +trans[0]*point2[0]+" "+ trans[3]*point2[0]+" "+ trans[6]*point2[0]);

                    //Applying second line transitions
                        newx1 = point2[0]*trans[0]+ point2[1]*trans[3]+ point2[2]*trans[6];
                        //Test logs
                            System.out.println("Point 2 trans: " +trans[0]+" "+ trans[3]+" "+ trans[6]);
                            System.out.println("trans + Point 2: " +trans[0]*point2[0]+" "+ trans[3]*point2[0]+" "+ trans[6]*point2[0]);

                        newy1 = point2[0]*trans[1]+ point2[1]*trans[4]+ point2[2]*trans[7];
                        //Test logs
                            System.out.println("Point 2 trans: " +trans[1]+" "+ trans[4]+" "+ trans[7]);
                            System.out.println("trans + Point 2: " +point2[0]*trans[1]+" "+ point2[1]*trans[4]+" "+ point2[2]*trans[7]);
                    //Assign newly transition points to point1 & point2 array
                    point1[0] = newx0;
                    point1[1] = newy0;
                    point2[0] = newx1;
                    point2[1] = newy1;
                }
                //  System.out.println("trans: Transition Matrix Ran: " + i);
                //Matrix representation to Line representation with transitions applied
                pointsArray[index][0]=point1[0]; //x1
                pointsArray[index][2]=point1[1]; //y1
                pointsArray[index][1]=point2[0]; //x2
                pointsArray[index][3]=point2[1]; //y2
                    System.out.println("Points Array: "
                        + pointsArray[index][0] + " "
                        + pointsArray[index][1] + " "
                        + pointsArray[index][2] + " "
                        + pointsArray[index][3]);
                ++index;
            System.out.println();
            }//For each pointsArray

        }
        //Connected to the Drawline Button
       public void addPoint(double x1,double x2, double y1, double y2){
        //For the first line
           if (pointsArray.length==0)
           {
               pointsArray = new double [1][4];
               //Assign to the first array at index 0
               pointsArray[0][0] = x1;
               pointsArray[0][1] = x2;
               pointsArray[0][2] = y1;
               pointsArray[0][3] = y2;
           }else{ //For multiple lines
               int prevRowCount = pointsArray.length;
               System.out.println(prevRowCount);
               double[][] temp = new double[pointsArray.length+1][4];
               System.arraycopy(pointsArray, 0, temp,0, pointsArray.length);
               //Assign to the last array at index prevRowCount
               temp[prevRowCount][0] = x1;
               temp[prevRowCount][1] = x2;
               temp[prevRowCount][2] = y1;
               temp[prevRowCount][3] = y2;
               pointsArray = temp;
           }

        }

        //Translate
    public void basicTranslate(double tx, double ty){
           int prevRowCount = TransitionMatrix.length;
           System.out.println("Previous Row Count in basicTranslate: "+ prevRowCount);
           double[][] temp = new double [TransitionMatrix.length+1][9];
           System.arraycopy(TransitionMatrix, 0, temp,0, TransitionMatrix.length);
           temp[prevRowCount]= new double[]{1,0,0,
                                         0,1,0,
                                         tx,ty,1};

           TransitionMatrix = temp;

    }
    //Basic Scale
    public void basicScale(double Sx, double Sy){
        int prevRowCount = TransitionMatrix.length;
        double[][] temp = new double [TransitionMatrix.length+1][9];
        System.arraycopy(TransitionMatrix, 0, temp,0, TransitionMatrix.length);
        temp[prevRowCount]= new double[]{Sx,0,0,
                                      0,Sy,0,
                                      0,0,1};
        TransitionMatrix = temp;
    }


    //Basic Rotation needs to be updated so it can handle doubles
    public void BasicRotate(double angle){
       double radians = Math.toRadians(angle);
        int prevRowCount = TransitionMatrix.length;
        double[][] temp = new double [TransitionMatrix.length+1][9];
        System.arraycopy(TransitionMatrix, 0, temp,0, TransitionMatrix.length);
        temp[prevRowCount]= new double[]{(double)Math.cos(radians),-1*((double)Math.sin(radians)),0,
                                    (double)Math.sin(radians),(double)Math.cos(radians),0,
                                    0,0,1};
        TransitionMatrix = temp;
    }


    //Transitions and scales
    public void Scale(double Sx,double Sy, double Cx, double Cy){
        int prevRowCount = TransitionMatrix.length;
        double[][] temp = new double [TransitionMatrix.length+3][9];
        System.arraycopy(TransitionMatrix, 0, temp,0, TransitionMatrix.length);
        temp[prevRowCount]= new double[]
                {1,0,0,
                0,1,0,
                -Cx,-Cy,1};
        temp[prevRowCount+1]= new double[]
                {Sx,0,0,
                0,Sy,0,
                0,0,1};
        temp[prevRowCount+2]= new double[]
                {1,0,0,
                0,1,0,
                Cx,Cy,1};
        TransitionMatrix = temp;
    }

    //Scales and rotates
    public void Rotate(double angle,double Cx, double Cy){
        double radians = Math.toRadians(angle);
        int prevRowCount = TransitionMatrix.length;
        double[][] temp = new double [TransitionMatrix.length+3][9];
        System.arraycopy(TransitionMatrix, 0, temp,0, TransitionMatrix.length);
        temp[prevRowCount]= new double[]
                        {1,0,0,
                        0,1,0,
                        -Cx,-Cy,1};
        temp[prevRowCount+1]= new double[]
                        {(double)Math.cos(radians),-1*(double)Math.sin(radians),0,
                        (double)Math.sin(radians),(double)Math.cos(radians),0,
                        0,0,1};
        temp[prevRowCount+2]= new double[]
                        {1,0,0,
                        0,1,0,
                        Cx,Cy,1};
        TransitionMatrix = temp;
    }


}
