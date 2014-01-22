using Apache.NMS;
using Apache.NMS.ActiveMQ;
using Apache.NMS.ActiveMQ.Commands;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Services
{
    public delegate void MessageReceivedDelegate(string message);

    public class TopicSubscriber : IDisposable
    {
        private readonly string topicName = null;
        private readonly IConnectionFactory connectionFactory;
        private readonly IConnection connection;
        private readonly ISession session;
        private readonly IMessageConsumer consumer;
        public event MessageReceivedDelegate OnMessageReceived;

        public TopicSubscriber(string topicName, string brokerUri, string clientId)
        {
            this.topicName = topicName;
            connectionFactory = new ConnectionFactory(brokerUri);
            connection = this.connectionFactory.CreateConnection();
            connection.ClientId = clientId;
            if (connection.IsStarted)
                connection.Stop();
            connection.Start();
            session = connection.CreateSession();
            var topic = new ActiveMQTopic(topicName);
            consumer = this.session.CreateDurableConsumer(topic,clientId, null, false);
            consumer.Listener += new MessageListener(OnMessage);
        }

        public void OnMessage(IMessage message)
        {
            ITextMessage textMessage = message as ITextMessage;
            if (this.OnMessageReceived != null)
            {
                this.OnMessageReceived(textMessage.Text);
            }
        }

        public void Dispose()
        {
            this.consumer.Dispose();
            this.session.Dispose();
            this.connection.Dispose();   
        }
    }
}
