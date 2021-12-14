## FGO日服抽卡
如果使用方法看不懂，有不明白的地方我也不想解答，我是菜批就写着玩玩的，真有问题我立马删库告辞。</br>
可以用来批量签到、领取礼物盒、抽卡等。</br>
## 使用方法
1.使用`jp_gacha.sql`创建库和表。</br>
2.运行程序，会生成一个`config.properties`文件，打开并正确填写数据库信息。</br>
3.重新运行程序，点击获取版本信息，成功后即可使用。</br>
4.该程序会自动领取礼物盒内的石头、苹果、友情点，会自动卖掉部分重复的低星从者，如不需要可以自行修改代码。</br>
5.线程数量是同时执行的账号数量，卡池id和从者id为必填，宝具数量填0则仅登录领取礼物盒和卖掉低星从者，不抽卡。</br>
## 数据库说明
order表必填字段`user_id`、`auth_key`、`sec_key`，获取方式可由存档文件解密获取（下面会提到）。</br>
proxy表可不填，如需批量跑号，ip被屏蔽之后可再次添加代理ip，格式为`123.123.123.123:1634`（IP:端口号）。</br>
## 存档获取及解密
存档文件在设备的位置`/sdcard/Android/data/com.aniplex.fategrandorder/files/data/54cc790bf952ea710ed7e8be08049531`， 用记事本或者文本浏览功能的软件打开，可看到加密后的字符串，从`ZSv`开始复制到最后，`ZSv/WkOGiQ......2A/xc2oyX/Bw==`。</br>
使用下面代码即可在控制台打印出带有`user_id`、`auth_key`、`sec_key`的json。</br>
```
import cn.mcfun.utils.FileExtractor;

public class test {
    public static void main(String[] args) throws Exception {
        FileExtractor fe = new FileExtractor();
        String data = fe.fileExtractor("ZSv/WkOGiQ......2A/xc2oyX/Bw==");
        System.out.println(data);
    }
}
```