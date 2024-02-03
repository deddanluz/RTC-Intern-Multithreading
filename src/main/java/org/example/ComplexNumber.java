package org.example;

public class ComplexNumber {
    private double real;
    private double imaginary;

    public ComplexNumber(){
        real=0.0;
        imaginary=0.0;
    }

    public ComplexNumber(double real, double imaginary){
        this.real=real;
        this.imaginary=imaginary;
    }

    public void addComplexNumber(ComplexNumber complexNumber){
        real=real+complexNumber.real;
        imaginary=imaginary+complexNumber.imaginary;
    }

    public ComplexNumber getComplexNumber(){
        return new ComplexNumber(real, imaginary);
    }

    public double modComplexNumber(){
        return Math.sqrt(Math.pow(real,2) + Math.pow(imaginary,2));
    }

    public ComplexNumber squareComplexNumber(){
        double _real = real*real - imaginary*imaginary;
        double _imaginary = 2*real*imaginary;
        return new ComplexNumber(_real,_imaginary);
    }

    public void multiplyComplexNumber(ComplexNumber complexNumber){
        double _real = real*complexNumber.real - imaginary*complexNumber.imaginary;
        double _imaginary = real*complexNumber.imaginary + imaginary*complexNumber.real;

        real = _real;
        imaginary = _imaginary;
    }

    public String toString(){
        return new String(real+" + "+imaginary+"i");
    }
}
