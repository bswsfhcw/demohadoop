package com.example.hadoop.servie;

import java.io.IOException;

public interface WordService  {
   public void count(String jobName ,String dirOrFile) throws IOException, InterruptedException, ClassNotFoundException;
}
