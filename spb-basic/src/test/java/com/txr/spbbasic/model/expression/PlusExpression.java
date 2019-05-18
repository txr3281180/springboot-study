package com.txr.spbbasic.model.expression;

public class PlusExpression extends Expression{

    @Override
    public void interpret(Context context) {
        //提示信息
        System.out.println("自动递增");
        //获得上下文环境
        String input = context.getInput();
        //进行类型转换
        int intInput = Integer.parseInt(input);
        //进行递增
        ++intInput;
        //对上下文环境重新进行赋值
        context.setInput(String.valueOf(intInput));
        context.setOutput(intInput);
    }
}
