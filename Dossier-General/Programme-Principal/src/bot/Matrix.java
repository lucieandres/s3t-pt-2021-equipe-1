package bot;

/**
 * Cette classe definit un espace de stockage et des régles mathématiques pour des matrices.
 * Ne sert qu'au réseau neuronal.
 */
public class Matrix{

  float[][] matrice;
  int x,y;

  Matrix(int x_, int y_){
    x = x_;
    y= y_;
    matrice = new float[x_][y_];
  }

  public int[] max_index(){
    float max_value = 0;
    int x_index = 0;
    int y_index = 0;
    for(int i = 0; i < x; i++){
          for(int j = 0; j < y; j++){
              if(matrice[i][j] > max_value){
                 max_value = matrice[i][j];
                 x_index = i;
                 y_index = j;
              }
         }
     }
    int[] coord = {x_index,y_index};
    return(coord);
  }

  public void calc_to(Matrix m){
    x = m.x;
    y= m.y;
    matrice = new float[m.x][m.y];
     for(int i = 0; i < x; i++){
          for(int j = 0; j < y; j++){
             matrice[i][j] = m.matrice[i][j];
         }
     }
  }

  public int truc(int[] value){
    return((int)(value[0]*y+value[1]));
  }

  public void transpose(){

    float[][] new_matrice = new float[y][x];

     for(int i = 0; i < x; i++){
          for(int j = 0; j < y; j++){

             new_matrice[j][i] = matrice[i][j];
         }
     }
     matrice = new_matrice;
     x = matrice.length;
     y = matrice[0].length;
  }

  public void randomize(float min, float max){
     for(int i = 0; i < x; i++){
          for(int j = 0; j < y; j++){
             matrice[i][j] = (float)(Math.random()*(max-min)+min);
         }
     }
  }

  public void activation_function(float smooth){
  for(int i = 0; i < x; i++){
          for(int j = 0; j < y; j++){
             matrice[i][j] = (float)((1/(1+Math.exp(smooth*matrice[i][j]))));
         }
     }
  }

  public float act_fun(float value,float smooth){
    return((float)((1/(1+Math.exp(smooth*value)))));
  }

  public void show(){
    System.out.println("");
    System.out.print("{");
    for(int i = 0; i < x; i++){
          for(int j = 0; j < y; j++){

             System.out.print(matrice[i][j]);
              System.out.print(";");
         }
        System.out.println("");
    }
    System.out.println("}");
  }

  public void derivative_function(float smooth){
    for(int i = 0; i < x; i++){
          for(int j = 0; j < y; j++){
              Double test_ultime = ((Math.exp(smooth*matrice[i][j])) / ( Math.pow(Math.exp(smooth*matrice[i][j])+1,2)));
            if(test_ultime.isNaN()){
              System.out.println("error");
            }else{
                matrice[i][j] = matrice[i][j] * ( 1 - matrice[i][j]);
            }

         }
     }
  }

  public void set_value(int x_, int y_, float value){
    matrice[x_][y_] = value;
  }

  public Matrix Add(float value){

    Matrix result = this;
       for(int i = 0; i < x; i++){
          for(int j = 0; j < y; j++){
              result.matrice[i][j] += value;
         }
     }
     return(result);
  }


  public void set_value_line(float[] array){

    if(x != 1){
      error("la matrice est plus grande que 1");
    }else{

      int loop = 0;
      for(float value: array){
        matrice[0][loop] = value;
        loop++;

      }
    }
  }


  public float[] get_line_value(int line){
	  if(y == 1){
		float[] result = new float[x];
		for(int i = 0; i < x; i++){
			result[i] = matrice[i][line];
		}
		return(result);
	  }else{
		  if(x == 1){
		    float[] result = new float[y];
		    for(int i = 0; i < y; i++){
		    	result[i] = matrice[line][i];
		    }
		    return(result);
		  }else{
		    float[] debug = {0};
		    return(debug);
		  }
	  }
 }

  public static void error(String error_message){
    System.out.println("");
    System.out.print("=======ERROR :");
    System.out.println(error_message);
    System.out.println("");
}

  public static Matrix multiply(Matrix factor1, Matrix factor2){
  if(factor1.y != factor2.x){
   error(" factor1 y ("+factor1.y+") et factor2 x ("+factor2.x+") ne correspondent pas");
   error(factor1.x+"   "+factor1.y+" | " + factor2.x+"   "+factor2.y);
   return(new Matrix(1,1));

  }else{


   Matrix result = new Matrix(factor1.x, factor2.y);
   for(int i = 0; i < result.y; i++){
     for(int j = 0; j < result.x; j++){

       float sum = 0;
       for(int k = 0; k < factor2.x ; k++){
             sum += factor1.matrice[j][k]*factor2.matrice[k][i];
       }
       result.set_value(j,i,sum);
     }
   }
   return(result);
  }
}

public static Matrix plus(Matrix term1, Matrix term2){
  if(term1.x != term2.x){
    error(" term1 x ("+term1.x+") et term2 x ("+term2.x+") ne correspondent pas");
    error(term1.x+"   "+term1.y+" | " + term2.x+"   "+term2.y);
    return(new Matrix(1,1));
  }else{
    if(term1.y != term2.y){
    error(" term1 y et term2 y ne correspondent pas");
    error(term1.x+"   "+term1.y+" | " + term2.x+"   "+term2.y);
    return(new Matrix(1,1));
    }else{
     Matrix result = new Matrix(term1.x,term1.y);
       for(int i = 0; i < result.x; i++){
          for(int j = 0; j < result.y; j++){
            if(!((Double)((double)(term1.matrice[i][j]+term2.matrice[i][j]))).isNaN()){
              if(term1.matrice[i][j]+term2.matrice[i][j] > 0.25){
              }
               result.set_value(i,j,term1.matrice[i][j]+term2.matrice[i][j]);
            }else{
            }
          }
       }
      return(result);
    }
  }
}

public static Matrix substract(Matrix term1, Matrix term2){
  if(term1.x != term2.x){
    error(" term1 x et term2 x ne correspondent pas");
    return(new Matrix(1,1));
  }else{
    if(term1.y != term2.y){
    error(" term1 y et term2 y ne correspondent pas");
    return(new Matrix(1,1));
    }else{
     Matrix result = new Matrix(term1.x,term1.y);
       for(int i = 0; i < result.x; i++){
          for(int j = 0; j < result.y; j++){
               result.set_value(i,j,term1.matrice[i][j]-term2.matrice[i][j]);
          }
       }
      return(result);
    }
  }
}

public static Matrix multi(Matrix factor, float multiplier){
     Matrix result = factor;
       for(int i = 0; i < result.x; i++){
          for(int j = 0; j < result.y; j++){
               result.set_value(i,j,result.matrice[i][j]*multiplier);
          }
       }
      return(result);
}

	public static Matrix fusion(Matrix factor, Matrix factor_2){

	  if(factor.x != factor_2.x){
	   error(" term1 x ("+factor.x+") et term2 x ("+factor_2.x+") ne correspondent pas");
	    error("  "+ factor.x+" |  " +    factor.y);
	    error("  "+ factor_2.x+" |  " +    factor_2.y);
	    return(new Matrix(1,1));
	  }else{
	    if(factor.y != factor_2.y){
	    error(" term1 y et term2 y ne correspondent pas");
	    return(new Matrix(1,1));
	    }else{
	     Matrix result = factor;
	       for(int i = 0; i < result.x; i++){
	          for(int j = 0; j < result.y; j++){

	               result.set_value(i,j,result.matrice[i][j]*factor_2.matrice[i][j]);
	          }
	       }
	      return(result);
	    }
	  }
	}
}
