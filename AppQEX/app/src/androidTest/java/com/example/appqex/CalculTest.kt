package com.example.appqex

import com.example.appqex.use_cases.data.Calcul
import org.junit.Assert
import org.junit.Test
import java.lang.StringBuilder

class CalculTest {
    val calc = Calcul()
    @Test
    fun lastIndexTest(){
        Assert.assertEquals(true,calc.lastIndexIsOk(StringBuilder("0101")))
        Assert.assertEquals(true,calc.lastIndexIsOk(StringBuilder("0")))
        Assert.assertEquals(true,calc.lastIndexIsOk(StringBuilder("-0101")))
        Assert.assertEquals(false,calc.lastIndexIsOk(StringBuilder("*")))
    }
    @Test
    fun replaceSymbol(){
        Assert.assertEquals("124/23424", calc.replaceSymbol("124÷23424"))
        Assert.assertEquals("-202*12", calc.replaceSymbol("-202×12"))
    }

}