package com.example.serializable.work

import java.io.*
import java.util.*


/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/24 23:17
 * pkn    : com.example.serializable.work
 * desc   :
 */
data class Course(var name: String, var score: Int) : Serializable {
    companion object {
        private val serialVersionUID = 667279791530738499L
    }
}

// 1.使用Externalizable序列化,反序列化必须有空构造函数
class Course1 : Externalizable {
    var name: String? = null
    var score = 0

    companion object {
        private val serialVersionUID = 667279791530738499L
    }

    constructor()
    constructor(name: String, score: Int) {
        this.score = score
        this.name = name
    }

    override fun readExternal(input: java.io.ObjectInput?) {
        println("readExternal")
        name = input?.readObject().toString()
        score = input?.readInt()!!
    }

    override fun writeExternal(out: java.io.ObjectOutput?) {
        println("writeExternal");
        out?.writeObject(name);
        out?.writeInt(score); }
}

class Student1(jiaoshi: String) : Student() {
    private var jiaoshi: String = jiaoshi
    override fun toString(): String {
        return "Student1(jiaoshi='$jiaoshi')"
    }
}

open class Student : Serializable {
    companion object {
        private val serialVersionUID = -2100492893943893602L // TODO 没有测试

        //2.静态成员变量属于类不属于对象，所以不会参与序列化(对象序列化保存的是对象的“状态”，
        // 也就是它的成员变量，因此序列化不会关注静态变量)
        // 静态成员属于类级别的，所以不能序列化，序列化只是序列化了对象而已，这里“不能序列化”的意思是序列化信息中不包含这个静态成员域
        // 是因为测试都在同一个机器（而且是同一个进程），因为这个jvm已经把i加载进来了，所以获取的是加载好的simpleDateFormat，
        // 如果是传到另一台机器或者关掉程序重新写个程序读入 序列.txt，此时因为别的机器或新的进程是重新加载simpleDateFormat的，所以simpleDateFormat信息就是初始时的信息，即启动时事件。
        private val simpleDateFormat: Long = Date().time
    }


    //3.反序列化一个类的过程中，它的非可序列化的属性将会调用无参构造函数重新创建
    //用transient关键字标记的成员变量不参与序列化(在被反序列化后，transient 变量的值被
    // 设为初始值，如 int 型的是 0，对象型的是 null)
    @Transient
    private var createTime: Date? = null
    private var name: String? = null
    private var sax: String? = null
    private var age: Int? = null

    //4.Course也需要实现Serializable接口,否则会出错:可序列化类中，未实现 Serializable 的属性状态无法被序列化/反序列化
    private var courses: MutableList<Course1>? = null


    constructor(name: String, sax: String, age: Int, createTime: Date, courses: MutableList<Course1>) {
        this.name = name
        this.sax = sax
        this.age = age
        this.createTime = createTime
        this.courses = courses
    }


    // 这个属性的无参构造函数必须可以访问，否者运行时会报错  TODO 没有测试出问题
    constructor() {
        println("Student: empty")
    }

    override fun toString(): String {
        return "Student(name=$name, sax=$sax, age=$age, createTime=$createTime, courses=$courses), simpleDateFormat=$simpleDateFormat)"
    }
}


fun main() {
//    val student = Student("张三", "男", 11, Date(),
//            mutableListOf(Course1("李四", 12), Course1("网二", 11)))

    val student = Student1("5024")
    println("student: $student")
    val out = ByteArrayOutputStream()
    val oos = ObjectOutputStream(out)

    /*val oos = ObjectOutputStream( FileOutputStream(
             File("test1.txt")))*/
    oos.writeObject(student)
//    student.score = 78f
// oos.reset();
    // oos.reset();
//    oos.writeUnshared(student)
// oos.writeObject(course);
    // oos.writeObject(course);
    val bs: ByteArray = out.toByteArray()
    oos.close()
    // Thread.sleep(5000)注意不可使用延迟测试static,因为static属于jvm,jvm加载类时就初始化了,以后不管做什么都不会变了,
    // 要启动两次,一次写入,关闭程序后在启动程序,加载,这时就会发现时间不同
    //  25 00:34:24 CST 2020
    //  25 00:34:48 CST 2020
    val ois = ObjectInputStream(ByteArrayInputStream(bs))
    /*  val ois = ObjectInputStream( FileInputStream(
               File("test1.txt")))*/
    val student1 = ois.readObject()
//    val student2 = ois.readObject() as Student
    println("student1: $student1")
//    println("student2: $student2")
//    println(student1 == student2)
//    println(student1.equals(student2))
}