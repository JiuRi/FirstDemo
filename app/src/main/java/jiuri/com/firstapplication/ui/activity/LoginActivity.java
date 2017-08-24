package jiuri.com.firstapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mob.tools.MobUIShell;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import jiuri.com.firstapplication.R;


/**
 * Created by user103 on 2017/7/20.
 */

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.edit_nama)
    EditText mEditNama;
    @BindView(R.id.edit_mima)
    EditText mEditMima;
    @BindView(R.id.logoing_button)
    Button mLogoingButton;
    private Toolbar mToolbar;
    private String mFrom;
    private EventHandler mEventHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        SMSSDK.setAskPermisionOnReadContact(false);
        // 处理你自己的逻辑
        //打开注册页面


        mEventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                Log.d("aa", "afterEvent: ________________"+event);
                Log.d("aa", "afterEvent: ________________"+result);

                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable) data;
                    String msg = throwable.getMessage();
                    Log.d("aa", "afterEvent: _________aaaaaa_______"+msg);
                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                    Intent intent1 =new Intent();
                    intent1.putExtra("date","success");
                    setResult(1,intent1);
                    finish();
                } else {
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        // 处理你自己的逻辑
                        Intent intent1 =new Intent();
                        intent1.putExtra("date","success");
                        setResult(1,intent1);
                        finish();
                    }
                }
            }
        };

        // 注册监听器
        SMSSDK.registerEventHandler(mEventHandler);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mFrom = getIntent().getStringExtra("from");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @OnClick({R.id.logoing_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.logoing_button:
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(mEventHandler);
                registerPage.show(this);
      /*          if (mFrom != null) {
                    if (mFrom.equals("newsdetail")) {
                        String name = mEditNama.getText().toString().trim();
                        String mima = mEditMima.getText().toString().trim();
                        if (TextUtils.isEmpty(name)||TextUtils.isEmpty(mima)) {
                            Toast.makeText(this, "账号或者密码为空请重新输入", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if (name.equals("zhongxu")&&mima.equals("123456")){
                                Intent intent1 =new Intent();
                                intent1.putExtra("date","success");
                                setResult(1,intent1);
                                finish();
                            }
                    }
                }

                }*/
                Intent intent =new Intent(this ,MobUIShell.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(mEventHandler);
    }
}
