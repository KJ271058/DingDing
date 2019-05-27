package com.example.administrator.testclient.login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.administrator.testclient.R;
import com.example.administrator.testclient.activity.MainActivity;
import com.example.administrator.testclient.bean.Student;
import com.example.administrator.testclient.util.HttpUtils;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = "Login";
    private EditText account;
    private EditText password;
    private TextView forget_password;
    private TextView register;
    private Button submit;
    private ImageView wx_login;
    private ImageView wb_login;
    private ImageView qq_login;
    private String accountString;
    private String passwordString;
    private boolean verification = false;
    private ToggleButton password_on;
    private ZLoadingDialog zLoadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        setEdUser();
    }

    private void setEdUser() {
        Student student = HttpUtils.getUser();
        if (student != null) {
            account.setText(student.getStuid());
            password.setText(student.getStupassword());
           submit();
        }
    }

    private void initView() {
        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        forget_password = (TextView) findViewById(R.id.forget_password);
        register = (TextView) findViewById(R.id.register);
        submit = (Button) findViewById(R.id.submit);
        wx_login = (ImageView) findViewById(R.id.wx_login);
        wb_login = (ImageView) findViewById(R.id.wb_login);
        qq_login = (ImageView) findViewById(R.id.qq_login);
        password_on = (ToggleButton) findViewById(R.id.password_on);

        password_on.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    password.setSelection(password.length());

                } else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    password.setSelection(password.length());
                }
            }
        });

        submit.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    private void setEdtextError(String message, EditText editText) {
        verification = false;
        ForegroundColorSpan fgcspan = new ForegroundColorSpan(Color.RED);
        SpannableStringBuilder ssbuilder = new SpannableStringBuilder(message);
        ssbuilder.setSpan(fgcspan, 0, message.length(), 0);
        editText.setError(ssbuilder);
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                submit();
                break;
            case R.id.register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    private void submit() {
        // validate
        String message = null;
        accountString = account.getText().toString().trim();
        if (TextUtils.isEmpty(accountString)) {
            message = "账号不能为空";
        }
        if (message != null) {
            setEdtextError(message, account);
            return;
        }


        passwordString = password.getText().toString().trim();
        if (TextUtils.isEmpty(passwordString)) {
            message = "密码不能为空";

        } else if (8 > passwordString.length() || passwordString.length() > 18) {
            message = "密码长度为8~18位";

        } else if (!checkpassword(passwordString)) {
            message = "密码长度为8~18位必须包含大小写字母和数字的组合，不能使用特殊字符";
        }
        if (message != null) {
            setEdtextError(message, password);
            return;
        }

        // TODO validate success, do something

        TestLogin();
        login(accountString, passwordString);

    }

    private void TestLogin() {
        zLoadingDialog = new ZLoadingDialog(LoginActivity.this);
        zLoadingDialog.setLoadingBuilder(Z_TYPE.DOUBLE_CIRCLE)
                .setLoadingColor(Color.RED)
                .setHintText("登陆中")
                .show();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        zLoadingDialog.cancel();
        Toast.makeText(LoginActivity.this, "登录成功！！", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }


    private void login(String account, String password) {
        zLoadingDialog = new ZLoadingDialog(LoginActivity.this);
        zLoadingDialog.setLoadingBuilder(Z_TYPE.DOUBLE_CIRCLE)
                .setLoadingColor(Color.RED)
                .setHintText("登陆中")
                .show();
        JSONObject object = new JSONObject();
        try {
            object.put("stuid", account);
            object.put("stupassword", password);
            HttpUtils.post("login", object, new HttpUtils.MyResponseHandler() {
                @Override
                protected void mySuccess(String s) {
                    Student stu=HttpUtils.toObject(s,Student.class);
                    HttpUtils.saveUser(stu);
                    zLoadingDialog.cancel();
                    Toast.makeText(LoginActivity.this, "登录成功！！", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                }

                @Override
                protected void myError(Throwable throwable, boolean b) {
                    zLoadingDialog.cancel();
                    Toast.makeText(LoginActivity.this, "请检查网络！！", Toast.LENGTH_SHORT).show();
                }

                @Override
                protected void myCancelled(CancelledException e) {

                }

                @Override
                protected void myFinished() {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkpassword(String birthday) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,18}$";
        return Pattern.matches(regex, birthday);
    }
}
