package com.example.appqex.use_cases.data

import android.widget.TextView
import com.example.appqex.presenters.AdapterForHistory
import com.example.appqex.use_cases.data.database.Calculations
import com.example.appqex.use_cases.data.database.CalculationsDatabase
import kotlinx.coroutines.*
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable
import java.lang.StringBuilder

class Calcul {
    fun getResult(builderForView: StringBuilder, db: CalculationsDatabase,
                  _list: List<Calculations>, adapter: AdapterForHistory,
                  txt:TextView){
        var list = _list

        try {
            if(lastIndexIsOk(builderForView)) {
                val context = Context.enter()
                context.optimizationLevel = -1
                val scope: Scriptable = context.initStandardObjects()
                var s = builderForView.toString()
                println(s)
                val result =
                        context.evaluateString(scope, replaceSymbol(s),
                                "<cmd>", 1, null)

                CoroutineScope(Dispatchers.IO).launch {
                    var calculation =
                            Calculations("${replaceSymbol(s)}=${result}")

                    db.calculationsDao()?.insert(calculation)

                    val request = async { db.calculationsDao()?.getFromDb() }
                    val resultdb = request.await()
                    list = resultdb!!

                    withContext(Dispatchers.Main){
                        adapter.update(ArrayList<Calculations>(list))
                        txt.text = result.toString()
                        builderForView.setLength(0)
                    }
                }
            }
        }
        catch (e:ArrayIndexOutOfBoundsException){
            print("ERROR")
        }

    }
    fun lastIndexIsOk(builderForView:StringBuilder):Boolean{
        var symbol = builderForView.toString().toCharArray()[builderForView.lastIndex]
        return (!symbol.equals('+')
                && !symbol.equals('-')
                && !symbol.equals('*')
                && !symbol.equals('/'))
    }
    fun replaceSymbol(s:String):String{
        val resultString1 = s.replace("÷", "/")
        return resultString1.replace("×", "*")

    }
}