package javaDesignPatterns;

/**
 * @author li_hhui
 * @date:2020年3月27日
 * @version:
 * 行为型模式
 * 观察者模式
 * 定义对象间一种一对多的依赖关系，使得当每一个对象状态发生时，其相关依赖对象能够得到通知并做出相应。
 * 也可称为发布订阅模式
 * java 消息服务JMS就是使用观察者模式，举例 activemq 使用发布订阅模式时 ,客户端将消息发送队列中，将这一事件称为被观察者，所以监听了此消息队列的消费者称为观察者。
 * 
 * 当被监听的队列上有消息时，观察者就会获取消息 进行消费。
 */
public class ObServerModelTest {

}
