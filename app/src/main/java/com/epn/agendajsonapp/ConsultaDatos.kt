package com.epn.agendajsonapp

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_consulta_datos.*
import org.json.JSONException
import org.json.JSONObject


class ConsultaDatos : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_datos)

        ConsultarContactos()
        ConsultarContactos()
    }
    @SuppressLint("SetTextI18n")
    fun ConsultarContactos() {

        val queue: RequestQueue = Volley.newRequestQueue(this)
        val url2 = "https://api.androidhive.info/contacts/"
        val jsObjRequest = JsonObjectRequest(Request.Method.GET, url2, null, Response.Listener <JSONObject>  { response ->
            try {
                val jsonObj = JSONObject(response.toString())
                val contacts = jsonObj.getJSONArray("contacts")


                for (i in 0 until contacts.length()) {
                    val contacto = arrayOf<String>()
                    val c = contacts.getJSONObject(i)

                    val textView1 = TextView(this)
                    textView1.text = c.getString("id")

                    val textView2 = TextView(this)
                    textView2.text = c.getString("name")

                    val textView3 = TextView(this)
                    textView3.text = c.getString("email")

                    val textView4 = TextView(this)
                    textView4.text = c.getString("address")

                    val textView5 = TextView(this)
                    textView5.text = c.getString("gender")


                    val phone = c.getJSONObject("phone")
                    val mobile = phone.getString("mobile")
                    val home = phone.getString("home")
                    val office = phone.getString("office")

//dar color++++++++++++++++++++++++++++++++
                    val textView6 = TextView(this)
                    val pho= "<font color='red'>Mobile:  $mobile</font> <font color='blue'><BR>Home: $home</font>  <font color='gray'> <BR>" +
                            "Office: $office</font>"
                    textView6.text = pho


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        textView6.setText(Html.fromHtml(pho,  Html.FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);
                    } else {
                        textView6.setText(Html.fromHtml(pho), TextView.BufferType.SPANNABLE);
                    }
////++++++++++++++++++++++++++++++++++++++

                    val row = TableRow(this)
                    row.addView(textView1)
                    row.addView(textView2)
                    row.addView(textView3)
                    row.addView(textView4)
                    row.addView(textView5)
                    row.addView(textView6)


                    tableLayoutContactos.addView(row)
                }
            }
            catch (e: JSONException) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
            }
        },
            Response.ErrorListener { error ->
                Toast.makeText(this, "That didn't work! " + error.toString(), Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(jsObjRequest)




    }
}
