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
    public class TopicPublisher : IDisposable
    {
        private readonly string topicName = null;
        private readonly IConnectionFactory connectionFactory;
        private readonly IConnection connection;
        private readonly ISession session;
        private readonly IMessageProducer producer;

        public TopicPublisher(String topicName, string brokerUri)
        {
            this.topicName = topicName;
            this.connectionFactory = new ConnectionFactory(brokerUri);
            this.connection = connectionFactory.CreateConnection();
            this.connection.Start();
            this.session = connection.CreateSession();
            ActiveMQTopic topic = new ActiveMQTopic(topicName);
            this.producer = session.CreateProducer(topic);
            this.producer.DeliveryMode = MsgDeliveryMode.Persistent;
        }

        public void SendMessage(String msg,DateTime? LiveTime = null, DateTime? Delivery = null)
        {
            ITextMessage txtMessage = session.CreateTextMessage(msg);
            txtMessage.NMSDeliveryMode = MsgDeliveryMode.Persistent;
            if(LiveTime != null)
            {
                var time = TimeSpan.FromMilliseconds(((DateTime)LiveTime).Subtract(DateTime.Now).TotalMilliseconds);
                txtMessage.NMSTimeToLive = time;
            }

            if(Delivery != null)
            {
                long time = (long)((DateTime)Delivery).Subtract(DateTime.Now).TotalMilliseconds;
                txtMessage.Properties["AMQ_SCHEDULED_DELAY"] = time;
            }
            producer.Send(txtMessage);
        }

        public void Dispose()
        {
            if (this.producer != null)
                this.producer.Dispose();
            if (this.session != null)
                this.session.Dispose();
            if (this.connection != null)
                this.connection.Dispose();
        }
    }
}
