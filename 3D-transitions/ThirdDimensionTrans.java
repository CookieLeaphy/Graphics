public class ThirdDimensionTrans {
    private double [][] TransitionMatrix = new double[0][0];
    private double [][] pointsArray = new double[0][0]; //World Coordinates
    private double [][] eyeArray = new double[0][0]; //Eye Coordinate
    private double [][] twoDimension; // converts the 3D space into a 2D {x0, x1, y0 ,y1}
    private double [] viewingTransformation = new double[16];


/*
    private double [] viewingTransformation = new double[]{
            -7.2, -1.4, -0.5, 0,
            2.4, -1.9, -0.6, 0,
            0, 3.2, -0.6, 0,
            0, 0, 12.5, 1
    };
   */

    public ThirdDimensionTrans() {
        //Initialize list with 4 integers containing points

    }
    //For calculations
    public double[][] getTransitionMatrix(){
        return TransitionMatrix;
    }

    public double[][] getTwoDimension(){
        applyViewingTransformations();
        return twoDimension;
    }

    //For drawing lines
    public double[][] getPointsArray(){
        return pointsArray;
    }

    //Clear lines
    public void clearLines(){

        pointsArray = new double[0][0];
        eyeArray = new double [0][0];
        twoDimension = new double[0][0];
    }

    public void clearTransitions(){
        TransitionMatrix = new double[0][0];
    }

    //The line is represented as x1, x2, y1, y2 so appropiate coordinate need assignment
    public void applyTransitions(){

        int index = 0;
        double[] point1 = new double[3], point2 = new double[3];
        for (double[] coord: pointsArray) {
            //Line representation to matrix representation;
            point1 = new double[]{coord[0], coord[2], coord[4],1};
            point2 = new double[]{coord[1], coord[3], coord[5],1};

            /*
            System.out.println("Point 1: "+ point1[0] + " "+ point1[1] + " " + point1[2] + " " + point1[3]);
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
            */

            //New variables to store original values
            double newx0, newx1, newy0, newy1, newz0, newz1;

            //Go through Geometric Transitions
            for (double[] trans : TransitionMatrix) {
                //Starting point dot product
                System.out.println("point1[0] before trans: " + point1[0]);
                System.out.println("point1[1] before trans: " + point1[1]);
                System.out.println("point1[2] before trans: " + point1[2]);
                System.out.println("point2[0] before trans: " + point2[0]);
                System.out.println("point2[1] before trans: " + point2[1]);
                System.out.println("point2[2] before trans: " + point2[2]);

                //Applying transitions to x0
                newx0 = (point1[0]*trans[0])+ (point1[1]*trans[4])+ (point1[2]*trans[8]) + (point1[3]*trans[12]);
                //Test Logs
                System.out.println("x0: "+ point1[0] + " --> " + newx0);
//                System.out.println("trans x0: " +  trans[0]+" "+ trans[4]+" "+ trans[8] + " " + trans[12]);
//                System.out.println("\t-x0 trans: " +  trans[0]*point1[0]+" "+ point1[1]*trans[4] +" "+ point1[2]*trans[8] + " "+ point1[3]*trans[12]);

                //Applying transitions to y0
                newy0 = (point1[0]*trans[1])+ (point1[1]*trans[5])+ (point1[2]*trans[9]) + (point1[3]*trans[13]);
                //Test logs
                System.out.println("y0: "+ point1[1]+ " --> " + newy0);
//                System.out.println("trans1: " +  trans[1]+" "+ trans[5]+" "+ trans[9]+" "+trans[13]);
//                System.out.println("\t-y0 trans: " +trans[1]*point1[0]+" "+ trans[5]*point1[0]+" "+ trans[9]*point1[0] + " "+ trans[13]*point1[0]);

                newz0 = (point1[0]*trans[2])+ (point1[1]*trans[6])+ (point1[2]*trans[10]) + (point1[3]*trans[14]);
                System.out.println("z0: "+ point1[2]+ " --> " + newz0);
//                System.out.println("trans z0: "+ trans[2] + " " + trans[6] + " " + trans[10] + " "+ trans[14]);
//                System.out.println("\t-z0 trans: " +trans[2]*point1[0]+" "+ trans[6]*point1[1]+" "+ trans[10]*point1[2] + " "+ trans[14]*point1[3]);

                //Test logs

                //Applying second line transitions
                newx1 = (point2[0]*trans[0])+ (point2[1]*trans[4])+ (point2[2]*trans[8]) + (point2[3]*trans[12]);
                //Test logs
                System.out.println("x1: "+ point2[0]+ " --> " + newx1);
//                System.out.println("Point x1: " +trans[0]+" "+ trans[4]+" "+ trans[8]+ " "+ trans[14]);
//                System.out.println("\t-trans x1: " +trans[0]*point2[0]+" "+ trans[4]*point2[1]+" "+ trans[8]*point2[2]+ trans[12]*point2[3]);

                newy1 = (point2[0]*trans[1])+ (point2[1]*trans[5])+ (point2[2]*trans[9]) + (point2[3]*trans[13]);
                //Test logs
                System.out.println("y1: "+ point2[1]+" --> " + newy1);
                System.out.println("Point y1: " +trans[1]+" "+ trans[5]+" "+ trans[9]+ " " + trans[13]);
                System.out.println("\t-trans x2: " +point2[0]*trans[1]+" "+ point2[1]*trans[5]+" "+ point2[2]*trans[9] + " "+ point2[3]*trans[13]);

                newz1 = (point2[0]*trans[2])+ (point2[1]*trans[6])+ (point2[2]*trans[10]) + (point2[3]*trans[14]);
                //Test logs
                System.out.println("z1: "+ point2[2]+" --> " + newz1);
//                System.out.println("Point z1: " +trans[2]+" "+ trans[6]+" "+ trans[10]+ " " + trans[14]);
//                System.out.println("\t-trans x2: " +point2[0]*trans[2]+" "+ point2[1]*trans[6]+" "+ point2[2]*trans[10] + " "+ point2[3]*trans[14]);

                //Assign newly transition points to point1 & point2 array
                point1[0] = newx0;  //x0
                point1[1] = newy0;
                point1[2] = newz0;
                point2[0] = newx1;
                point2[1] = newy1;
                point2[2] = newz1;
            }
            //  System.out.println("trans: Transition Matrix Ran: " + i);
            //Matrix representation to Line representation with transitions applied
            pointsArray[index][0]=point1[0]; //x1
            pointsArray[index][1]=point2[0]; //x2
            pointsArray[index][2]=point1[1]; //y1
            pointsArray[index][3]=point2[1]; //y2
            pointsArray[index][4]=point1[2]; //z1
            pointsArray[index][5]=point2[2]; //z2
            System.out.println("Points Array: "
                    + pointsArray[index][0] + " " //x1
                    + pointsArray[index][1] + " " //x2
                    + pointsArray[index][2] + " " //y1
                    + pointsArray[index][3] + " " //y2
                    + pointsArray[index][4] + " " //z1
                    + pointsArray[index][5]);     //z2
            ++index;
            System.out.println("");
        }//For each pointsArray

        System.out.println("End of transition\n\n");
    }


    //Connected to the Drawline Button
    public void addPoint(double x1,double x2, double y1, double y2, double z1, double z2){
        //For the first line
        if (pointsArray.length==0)
        {
            pointsArray = new double [1][6];
            //Assign to the first array at index 0
            pointsArray[0][0] = x1;
            pointsArray[0][1] = x2;
            pointsArray[0][2] = y1;
            pointsArray[0][3] = y2;
            pointsArray[0][4] = z1;
            pointsArray[0][5] = z2;

        }else{ //For multiple lines

            int prevRowCount = pointsArray.length;
            System.out.println(prevRowCount);
            double[][] temp = new double[pointsArray.length+1][6];
            System.arraycopy(pointsArray, 0, temp,0, pointsArray.length);
            //Assign to the last array at index prevRowCount
            temp[prevRowCount][0] = x1;
            temp[prevRowCount][1] = x2;
            temp[prevRowCount][2] = y1;
            temp[prevRowCount][3] = y2;
            temp[prevRowCount][4] = z1;
            temp[prevRowCount][5] = z2;
            pointsArray = temp;
        }

    } //addPoint

    //Translate
    public void basicTranslate(double tx, double ty, double tz){
        int prevRowCount = TransitionMatrix.length;
        System.out.println("Previous Row Count in basicTranslate: "+ prevRowCount);
        double[][] temp = new double [TransitionMatrix.length+1][16];
        System.arraycopy(TransitionMatrix, 0, temp,0, TransitionMatrix.length);
        temp[prevRowCount]= new double[]{
                1,0,0,0,
                0,1,0,0,
                0,0,1,0,
                tx,ty,tz, 1};

        TransitionMatrix = temp;

    }
    //Basic Scale
    public void basicScale(double Sx, double Sy, double Sz){
        int prevRowCount = TransitionMatrix.length;
        double[][] temp = new double [TransitionMatrix.length+1][16];
        System.arraycopy(TransitionMatrix, 0, temp,0, TransitionMatrix.length);
        temp[prevRowCount]= new double[]{
                Sx,0,0,0,
                0,Sy,0,0,
                0,0,Sz,0,
                0,0,0,1};
        TransitionMatrix = temp;
    }


    //Basic Rotation needs to be updated so it can handle doubles
    public void BasicRotate(double angle, String axis){
        double radians = Math.toRadians(angle);
        int prevRowCount = TransitionMatrix.length;
        double[][] temp = new double [TransitionMatrix.length+1][16];
        System.arraycopy(TransitionMatrix, 0, temp,0, TransitionMatrix.length);
        if(axis.equalsIgnoreCase("z")) {
            temp[prevRowCount] = new double[]{
                    (double) Math.cos(radians), (double) Math.sin(radians), 0, 0,
                    -1*(double) Math.sin(radians), (double) Math.cos(radians), 0, 0,
                    0, 0, 1, 0,
                    0, 0, 0, 1};
        }//x
        else if(axis.equalsIgnoreCase("y")){
            temp[prevRowCount] = new double[]{
                    (double) Math.cos(radians),0, -1*(double) Math.sin(radians), 0,
                    0,1,0,0,
                    (double) Math.sin(radians), 0, (double) Math.cos(radians), 0,
                    0, 0, 0, 1};
        }else{
            temp[prevRowCount] = new double[]{
                    1,0,0,0,
                    0,(double) Math.cos(radians), (double) Math.sin(radians),0,
                    0,-1*(double) Math.sin(radians), (double) Math.cos(radians), 0,
                    0, 0, 0, 1};
        }
        TransitionMatrix = temp;
    }


    //Transitions and scales
    public void Scale(double Sx,double Sy, double Sz, double Cx, double Cy, double Cz){
        int prevRowCount = TransitionMatrix.length;
        double[][] temp = new double [TransitionMatrix.length+3][16];
        System.arraycopy(TransitionMatrix, 0, temp,0, TransitionMatrix.length);
        temp[prevRowCount]= new double[]
                {
                        1,0,0,0,
                        0,1,0,0,
                        0,0,1,0,
                        -Cx,-Cy,-Cz,1};
        temp[prevRowCount+1]= new double[]
                {
                        Sx,0,0,0,
                        0,Sy,0,0,
                        0,0,Sz,0,
                        0,0,0,1};
        temp[prevRowCount+2]= new double[]
                {
                        1,0,0,0,
                        0,1,0,0,
                        0,0,1,0,
                        Cx,Cy,Cz,1};
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



    //Convert the world coordinates into eye coordinates and then into 2d screen coordinates
    public void applyViewingTransformations(){
//        eyeArray = pointsArray;
        twoDimension = new double[pointsArray.length][4];
        double x = -6;
        double y = -8;
        double z = -7.5;
        double [] t1 = new double[]{
                1,0,0,0,
                0,1,0,0,
                0,0,1,0,
                x,y,z,1}; //-6,-8,-7.5
        viewingTransformation=t1;
        System.out.println("Viewport Trans Array:");
        for (int i = 0; i< 16; i++){
            System.out.print("t1 "+ viewingTransformation[i] + " ");
        }
        System.out.println();
        double [] t2 = new double[]{
                1,0, 0,0,
                0,0,-1,0,
                0,1, 0,0,
                0,0, 0,1};
        viewingTransformation=multiplyMatrix(viewingTransformation,t2);

        System.out.println("Viewport Trans Array:");
         for (int i = 0; i< 16; i++){
            System.out.print(viewingTransformation[i] + " ");
        }
        System.out.println();
        double angle1 = y / (Math.sqrt(Math.pow(x,2)+Math.pow(y,2)));  //.8
        double angle2 = x / (Math.sqrt(Math.pow(y,2)+Math.pow(x,2)));  //.6

        double [] t3 = new double[]{
                angle1, 0, -1*angle2, 0,
                   0, 1,   0, 0,
                angle2, 0,angle1, 0,
                   0, 0,   0, 1};
        viewingTransformation=multiplyMatrix(viewingTransformation,t3);
        double angletheta1 = Math.sqrt(Math.pow(x,2)+Math.pow(y,2))/Math.sqrt(Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2));
        double angletheta2 = z/(Math.sqrt(Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2)));
        System.out.println("angle " + angle1 + " "+ angle2+ " " +angletheta1 + " "+ angletheta2);
        double [] t4 = new double[] {
                1,    0,   0,  0,
                0,  angletheta1, -1*angletheta2,  0,
                0, angletheta2, angletheta1,  0,
                0,    0,   0,  1};
        viewingTransformation=multiplyMatrix(viewingTransformation,t4);
        double [] t5 = new double[] {
                1,0,0,0,
                0,1,0,0,
                0,0,-1,0
                ,0,0,0,1};
        viewingTransformation=multiplyMatrix(viewingTransformation,t5);

        double d = 60;
        double s = 15;
        double[] n1 = new double[] {
                d/s,   0, 0, 0,
                0, d/s, 0, 0,
                0,   0, 1, 0,
                0,   0, 0, 1,
        };
        viewingTransformation=multiplyMatrix(viewingTransformation,n1);

        System.out.println("Viewport Trans Array:");
        for (int i = 0; i< 16; i++){
            System.out.print(viewingTransformation[i] + " ");
        }
        System.out.println();
        int index = 0;
        double newx0, newy0, newx1, newy1, newz0, newz1;
        double[] point1, point2;
        for (double[] coord: pointsArray) {
            point1 = new double[]{coord[0], coord[2], coord[4], 1};
            point2 = new double[]{coord[1], coord[3], coord[5], 1};



            //Applying view transformation
            newx0 = (point1[0] * viewingTransformation[0]) + (point1[1] * viewingTransformation[4]) + (point1[2] * viewingTransformation[8]) + (point1[3] * viewingTransformation[12]);
            newy0 = (point1[0] * viewingTransformation[1]) + (point1[1] * viewingTransformation[5]) + (point1[2] * viewingTransformation[9]) + (point1[3] * viewingTransformation[13]);
            newz0 = (point1[0] * viewingTransformation[2]) + (point1[1] * viewingTransformation[6]) + (point1[2] * viewingTransformation[10]) + (point1[3] * viewingTransformation[14]);
            newx1 = (point2[0] * viewingTransformation[0]) + (point2[1] * viewingTransformation[4]) + (point2[2] * viewingTransformation[8]) + (point2[3] * viewingTransformation[12]);
            newy1 = (point2[0] * viewingTransformation[1]) + (point2[1] * viewingTransformation[5]) + (point2[2] * viewingTransformation[9]) + (point2[3] * viewingTransformation[13]);
            newz1 = (point2[0] * viewingTransformation[2]) + (point2[1] * viewingTransformation[6]) + (point2[2] * viewingTransformation[10]) + (point2[3] * viewingTransformation[14]);

            //Assign newly transition points to point1 & point2 array
            point1[0] = newx0;
            point1[1] = newy0;
            point1[2] = newz0;
            point2[0] = newx1;
            point2[1] = newy1;
            point2[2] = newz1;

            //Eye Array
//            eyeArray[index][0]=point1[0]; //x1
//            eyeArray[index][2]=point1[1]; //y1
//            eyeArray[index][4]=point1[2]; //z1
//            eyeArray[index][1]=point2[0]; //x2
//            eyeArray[index][3]=point2[1]; //y2
//            eyeArray[index][5]=point2[2]; //z2

            //Convert eyeArray points to screen coordinates so that we can drawline with them
            /*
            twoDimesion[index][
            Xs = (Xc / Zc)(Vsz + Vcx)
            Ys = (Ys / Zc)(Vsy + Vcy)
            */
            System.out.println("Converting eyeCoordinates to screen coordinate");
            twoDimension[index][0] = (newx0/newz0)*200 + 200; //x1s
            twoDimension[index][1] = (newx1/newz1)*200+  200;//y1s

            twoDimension[index][2] = (newy0/newz0)*200 + 200;
            twoDimension[index][3] = (newy1/newz1)*200 + 200;

            System.out.println("Screen Coordinates: " + index);
            System.out.println(
                    twoDimension[index][0] + " " +
                    twoDimension[index][1] + " " +
                    twoDimension[index][2] + " " +
                    twoDimension[index][3]
            );

            //increment
            index++;



        }

//        double sumOfRow;
//        double[][] temp = new double[4][4];
        //world --> eye
        //eyeArray * viewingTransformation

    }//applyViewingTransformation

    public double[] multiplyMatrix(double[] array1,double[] array2 ){
       double[] temp = new double[array1.length];
       double sumOfRow;
        for (int i = 0; i < 4; i++) { //rows
            for (int j = 0; j < 4; j++) { //cols
                sumOfRow = 0;
                for(int k = 0; k < 4; k++) { //x
                    //temp[(i+1)*(j+1)-1] += array1[(i+1)*(k+1)-1] * array2[4*(k+1)*(j+1)-1]; //so index 0 doesn't loose any values
                    sumOfRow += array1[i*4+k] * array2[k*4+j];
                }
                temp[i*4+j]= sumOfRow;
            } //cols
        } //rows
        //eyeArray = temp;

        return temp;
        //row = 0
        //col = 3
        //multiplication factor 4
        // how to make it equal 8
    }
}
