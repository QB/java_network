
それぞれ「どのクラスのインスタンス」か(を返すか)知っておく必要がある
　static PrintStream	err 
			「標準」エラー出力ストリームです。
　static InputStream	in 
			「標準」入力ストリームです。
　static PrintStream	out 
			「標準」出力ストリームです

　new FileOutputStream(filename)
			ファイル書き込みのストリーム
　new FileInputStream(filename)
			ファイル読み込みのストリーム

　public InputStream getInputStream()throws IOException
　　　　　　　ソケットの入力ストリーム
　public OutputStream getOutputStream() throws IOException
			ソケットの出力ストリーム





InputStreamのread
public int read(byte[] b) throws IOException
入力ストリームから数バイトを読み込み、それをバッファー配列 b に格納します。実際に読み込まれたバイト数は整数として返されます。
このメソッドは、入力データが読み込めるようになるか、ファイルの終わりが検出されるか、あるいは例外がスローされるまでブロックします。

パラメータ:
　b - データの読み込み先のバッファ
戻り値:
　バッファに読み込まれたバイトの合計数。ストリームの終わりに達してデータがない場合は -1
例外:
　IOException - ファイルの終わりに達していること以外の理由で最初のバイトを読み込めない場合、
                入力ストリームが閉じられた場合、またはその他の入出力エラーが発生した場合
　NullPointerException - b が null の場合

"------------------------------------------------------------------------------------"

OutputStreamのwrite
public void write(byte[] b, int off, int len) throws IOException

指定されたバイト配列の、オフセット位置 off から始まる len バイトをこの出力ストリームに書き込みます。
write(b, off, len) の汎用規約では、配列 b 内の一定のバイトが出力ストリームに順番に書き込まれます。
この処理で最初に書き込まれるバイトは要素 b[off]、最後に書き込まれるバイトは要素 b[off+len-1] です。  
OutputStream の write メソッドは、書き込むバイトごとに 1 個の引数を持つ書き込みメソッドを呼び出します。
サブクラスでは、このメソッドをオーバーライドし、より効率的に実装してください。  

パラメータ:
　b - データ
　off - データの開始オフセット
　len - 書き込むバイト数　
例外:
　IOException - 入出力エラーが発生した場合特に、出力ストリームが閉じられると、IOException がスローされる

"------------------------------------------------------------------------------------"

FileInputStreamのread
public int read(byte[] b) throws IOException
最大 b.length バイトまでのデータを、この入力ストリームからバイト配列に読み込みます。

オーバーライド:
　クラス InputStream 内の read
戻り値:
　バッファに読み込まれたバイトの合計数。ストリームの終わりに達してデータがない場合は -1
例外:
　IOException - 入出力エラーが発生した場合

"------------------------------------------------------------------------------------"

FileOutputStreamのwrite
public void write(byte[] buf, int off, int len)
オフセット位置 off から始まる指定されたバイト配列から、このストリームに len バイトを書き込みます。

オーバーライド:
　クラス OutputStream 内の write
パラメータ:
　b - データ
　off - データの開始オフセット
　len - 書き込むバイト数
例外:
　IOException - 入出力エラーが発生した場合

"------------------------------------------------------------------------------------"



