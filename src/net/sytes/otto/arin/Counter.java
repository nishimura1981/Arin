package net.sytes.otto.arin;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Counter {
	private static final String PREFERRENCES_FILE_NAME = "PrefrencesFile";
	private Context context;
	public int n;
	private final int count_stop = 9999;

	public Counter(Context context){
		this.context = context;
		//プリファレンスのインスタンスを取得
        SharedPreferences pre = context.getSharedPreferences(PREFERRENCES_FILE_NAME,Context.MODE_PRIVATE);
        //読み込み
        n = pre.getInt("key", 0);
	}

	public void draw(Canvas canvas,int height,int width){
		Paint p = new Paint();
		p.setColor(Color.MAGENTA);
		p.setTextSize(50);
		String s = String.format("%1$04dあーりんだよぉ", read());
		int textWidth = (int) p.measureText( s);
		int x = width - textWidth;
		canvas.drawText(s, x/2, height-40, p);
	}


	public int read(){
		return n;
   	}

	public void write(int n){
		if(n > count_stop){
			return;
		}
		this.n = n;
		// プリファレンスの準備 //
        SharedPreferences pref =
                context.getSharedPreferences(PREFERRENCES_FILE_NAME, Context.MODE_PRIVATE );
        // プリファレンスに書き込むためのEditorオブジェクト取得 //
        Editor editor = pref.edit();
        // 登録
        editor.putInt( "key",n);
        // 書き込みの確定（実際にファイルに書き込む）
        editor.commit();
	}

	public int clear(){
		write(0);
		return 0;
	}

	public int add(){
		n = read();
		write(n+1);
		return n+1;
	}
}
