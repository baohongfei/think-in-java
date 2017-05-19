package com.baohongfei.tij4.annotations.p01;

import com.baohongfei.tij4.net.mindview.atunit.Test;

public class Testable
{
    public void execute()
    {
        System.out.println("Executing..");
    }

    @Test
    void testExecute()
    {
        execute();
    }
}
