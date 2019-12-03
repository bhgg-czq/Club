package com.czq.club;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClubManagerCreateActivity extends Activity implements View.OnClickListener{
    private String sql="select * from club";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_manager_createactivity);

        Button button=(Button)findViewById(R.id.button_commit_activity);
        TextView textname=(TextView)findViewById(R.id.text_activity_name);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        new Thread() {
            @Override
            public void run() {
                try {
                    acceptServer(sql);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    private void acceptServer(String sql) throws IOException {
        //1.创建客户端Socket，指定服务器地址和端口
        Socket socket = new Socket("192.168.43.92", 6799);
        //2.获取输出流，向服务器端发送信息
        OutputStream os = socket.getOutputStream();//字节输出流
        InputStream in = socket.getInputStream();
        PrintWriter pw = new PrintWriter(os);//将输出流包装为打印流
        //获取客户端的IP地址
        InetAddress address = InetAddress.getLocalHost();
        String ip = address.getHostAddress();
        pw.write(sql);
        pw.flush();
        socket.shutdownOutput();//关闭输出流
        socket.close();
    }

}
