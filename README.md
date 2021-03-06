# Kson: quick look 
--------------------------

[ ![Maven Central](https://api.bintray.com/packages/ymex/maven/kson/images/download.svg) ](https://bintray.com/ymex/maven/kson/_latestVersion)

Used for convenient parsing and searching of JSON strings, not a serialized or serialized JSON library. For complex JSON, we just need one of them
A section of JSON, which is convenient for gson and fastjson serialization, reduces the definition of an unrelated Bean class.
[中文说明](https://github.com/ymex/kson/blob/master/README-zh.md)
## grammar

The chain function structure is used to keep the code simple

### alias

The lookup key can use `：` to define aliases'. For example, `result:data`, in the result set, result is the alias of data.

### array

The use of `[]` search array

### multilevel search

The use of `->` to find multi.
For example, `result->books[2]->title` means the title. of the second element object of the array books under the result object

## Use
```
compile 'cn.ymex:kson:1.2.1'
```
json 的数据源可点击 查看：[json source ](https://github.com/ymex/kson/blob/master/app/src/main/assets/complex.json)

```
int money  = Kson.unmarshal(json).find("day:data->income[0][3][0]->day").get().Int();

Kson kson = Kson.unmarshal(json)
        .find("message", "result:data", "man:data->person",  "day:data->income[0][3][0]->day");

String message = kson.getFirst().string();
String result = kson.get("result").string();//等价 kson.get("data").string()
int day = kson.getLast().Int();
```

## note
Every time you call `find ()` method, the result set that was last searched will be emptied. To save the result, you can call the getall () method to get the result set of the query.

## best practice

If you find multiple elements, the best practice is to look for two at a time, so that you can get all of the query results using `getfirst ()` and `getlast () `methods.
Avoid using the `get (key)` method because you might have spelling errors when writing key.
