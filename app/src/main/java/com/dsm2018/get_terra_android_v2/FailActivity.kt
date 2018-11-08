package undefined.dsm.getterra.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.dsm2018.get_terra_android_v2.MainActivity
import com.dsm2018.get_terra_android_v2.R


class FailActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fail)
        val btn_gomian = findViewById<View>(R.id.btn_main) as Button
        /*btn_gomian.setOnClickListener(object : Button.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(this@FailActivity, MainActivity::class.java)
                startActivity(intent)
            }
        })*/
        btn_gomian.setOnClickListener {
            var Intent = Intent(this@FailActivity, MainActivity::class.java)
        }
    }
}
