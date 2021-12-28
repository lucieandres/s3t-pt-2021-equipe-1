package bot;

public class Neural_network{
  
  Matrix[] biais;
  Matrix[] memory;
  Matrix[] value;
  float act_fun_smooth = -2;
  float learning_rate = 0.01f;
  int resultat;
  
  Neural_network(int input, int output, int[] hidden){
    int[] layer = new int[hidden.length+2];
    for(int i = 1; i < hidden.length+1; i++){
      layer[i] = hidden[i-1];
    }
    layer[layer.length-1] = output;
    layer[0] = input;
    
    int loop_value = 0;
    int loop_memory = -1;
    memory = new Matrix[layer.length-1];
    biais = new Matrix[layer.length-1];
    value = new Matrix[layer.length];
    
    for(int v: layer){
      if(loop_memory >= 0){
        memory[loop_memory] = new Matrix(layer[loop_memory],v);
        biais[loop_memory] = new Matrix(1,v);
      }
      value[loop_value] = new Matrix(1,v);
      loop_value ++;
      loop_memory ++;
    }
  }
  
  public void set_FeedForward(float smooth, float lr){
    act_fun_smooth = smooth;
    learning_rate = lr;
  }
  
  
  public void train(float[] input,int t){
      calculate(input);
      float[] debug = new float[10];
      debug[t] = 1;
      Matrix target = new Matrix(1,10);
      target.set_value_line(debug);
      back_propagation(target);
  }
  
  public void reset(){
     for(int i = 0 ; i < memory.length; i++){
        memory[i].randomize((float)(-1),(float)(1));
        biais[i].randomize((float)(-1),(float)(1));
     }
  }

  public void calculate(float[] input){
     value[0].set_value_line(input);
    for(int i = 1; i < value.length; i++){
        Matrix sum = Matrix.multiply(value[i-1],memory[i-1]);
        sum = Matrix.plus(sum, biais[i-1]);
        sum.activation_function(act_fun_smooth);
        value[i] = sum;
    }
  }
  
  public void back_propagation(Matrix target){
    
    Matrix error = Matrix.substract(target , value[value.length-1]);
    for(int i = 0; i < memory.length ; i++){
      Matrix Wt = new Matrix(0,0);
      Wt.calc_to(memory[memory.length-1-i]);
      Wt.transpose();
      Matrix gradient = new Matrix(0,0);
      gradient.calc_to(value[value.length-1-i]);
      gradient.derivative_function(0);
      gradient = Matrix.fusion(gradient,error);
      gradient = Matrix.multi(gradient,learning_rate);
      Matrix Lt = new Matrix(0,0);
      Lt.calc_to(value[value.length-2-i]);
      Lt.transpose();
      Matrix delta = new Matrix(0,0);
      delta = Matrix.multiply(Lt , gradient);
      memory[memory.length-1-i] = Matrix.plus(memory[memory.length-1-i] , delta);
      biais[biais.length-1-i] = Matrix.plus(biais[biais.length-1-i],gradient);
      Matrix actErr = new Matrix(0,0);
      actErr = Matrix.multiply(error,Wt);
      error = actErr;
    }
  }

  int predict(){
    return(value[value.length-1].truc(value[value.length-1].max_index()));
  }
}
