package com.devseok95.gitsearch

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    var nameArr = ArrayList<String>()
    var descriptionArr = ArrayList<String>()
    var updated_atArr = ArrayList<String>()
    var html_urlArr = ArrayList<String>()
    var sb = StringBuffer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //listview = (ListView) findViewById(R.id.listview);
        button!!.setOnClickListener {
            val id = editText!!.text.toString()
            if (!id.isEmpty()) {
                val res = NetRetrofit.instance.service.getListRepos(id)
                res?.enqueue(object : Callback<ArrayList<JsonObject?>?> {
                    override fun onResponse(call: Call<ArrayList<JsonObject?>?>, response: Response<ArrayList<JsonObject?>?>) { //Log.d("TESTTEST", response.toString());
                        if (response.body() != null) {
                            val result = response.body().toString()
                            try {
                                val jsonArray = JSONArray(result)
                                var name = ""
                                var description = ""
                                var updated_at = ""
                                var html_url = ""
                                for (i in 0 until jsonArray.length()) {
                                    val jsonObject = jsonArray.getJSONObject(i)
                                    val `object` = jsonObject.toString()
                                    name = jsonObject.getString("name")
                                    description = jsonObject.getString("description")
                                    updated_at = jsonObject.getString("updated_at")
                                    html_url = jsonObject.getString("html_url")
                                    // object가 전체 열 부르기
                                    nameArr.add(name)
                                    descriptionArr.add(description)
                                    updated_atArr.add(updated_at)
                                    html_urlArr.add(html_url)
                                    sb.append("이름 : $name // 업데이트 날짜 : $updated_at\n")
                                }
                                textView!!.text = sb.toString()
                                //final ArrayAdapter<String> adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, html_urlArr);
//listview.setAdapter(adapter);
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<JsonObject?>?>, t: Throwable) {
                        Log.d("TESTTEST", t.message)
                    }
                })
            } else {
                Toast.makeText(applicationContext, "ID를 입력하세요.", Toast.LENGTH_SHORT).show()
            }
        }
        //        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selected_item = (String) parent.getItemAtPosition(position);
//
//                Toast.makeText(getApplicationContext(), selected_item, Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }
}