#说明文档
-------------------------

用于便捷解析和查找json 字串， 不是一个序列化或返序列化的json库。对于复杂的json而恰好我们只需要其中某
一段json，方便用于gson,fastjson反序列化而减少无关Bean类的定义。

##语法
采用链式函数结构保持代码简洁，

###别名
查找的key可以使用`:`来定义别名。如，result:data ，那么在结果 集中，result 就是data 的别名。
###数组
查找数组时使用`[]`
###多级查找 
多级查找 使用 `->`来表示。
如，"result->books[2]->title" 表示查找 result 对象下数组books 的第二个元素对象的title.

##使用


```
int money  = Kson.stream(json).find("day:data->income[0][3][0]->day").get().Int();

Kson kson = Kson.stream(json)
        .find("message", 
                "result:data", 
                "man:data->person", 
                "day:data->income[0][3][0]->day");

String message = kson.getfirst().string();
String result = kson.get("result").string();//等价 kson.get("data").string()
int day = kson.getlast().Int();

```

##注意
每次调用`find()`方法时上次查找的结果集将会清空。 若要保存结果，你可以调用getall()方法， 获得查询的结果集。

##最佳实践
若查找多个元素，最好的实践是每次查找两个，这样使用 `getfirst()`和`getlast()`方法就可以获取全部的查询结果。
避免使用`get(key)`方法,因为你写key 时可能 出现拼写错误。