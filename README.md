# documentation
-------------------------
Used for convenient parsing and searching of JSON strings, not a serialized or serialized JSON library. For complex JSON, we just need one of them
A section of JSON, which is convenient for gson and fastjson serialization, reduces the definition of an unrelated Bean class.

## grammar

The chain function structure is used to keep the code simple

### alias

The lookup key can use `：` to define aliases'. For example, `result:data`, in the result set, result is the alias of data.

### array

The use of `[]` search array

### multilevel search

The use of `->` to find multi.
For example, `result->books[2]->title` means the title. of the second element object of the array books under the result object

##Use

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

## note
Every time you call `find ()` method, the result set that was last searched will be emptied. To save the result, you can call the getall () method to get the result set of the query.

## best practice

If you find multiple elements, the best practice is to look for two at a time, so that you can get all of the query results using `getfirst ()` and `getlast () `methods.
Avoid using the `get (key)` method because you might have spelling errors when writing key.