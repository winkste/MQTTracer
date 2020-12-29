# MQTTracer

A JAVA MQTT client to manage and trace your MQTT devices.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
Eclipse PAHO library 3-1.2.2
```

### Installing

Clone or download the project to your local PC or notebook.

In the data package of the project create a class called ToolSets. In that class, the following functions have to be defined and have to return your user specific settings.

```
public class ToolSets 
{

    public String GetAddress() 
    {
        return("tcp://myMQTTBrokerIPAddress:myMQTTBrokerPort");
    }

    public String GetUser() 
    {
        return("myMQTTUserName");
    }

    public String GetPwd() 
    {
        return("myMQTTPwd");
    }
    
}
```

After adding this class, the project should compile and run.

## Testing

No automated tests build in so far, use manual functional testing.

### Coding Styles

Minimal coding styles used. See the code.

## Deployment

Deployed as packed .jar file in progress.

## Built With

NetBeans 8.2 Patch2
JAVA 1.8.0.25
* [NetBeans 8.2 Patch2](https://netbeans.org) - The JAVA Integrated Development Environment.
* [JAVA](https://www.java.com/de/download/) - JAVA runtime environment and JSE.

## Contributing

n/a.

## Versioning

TBD

## Authors

* **Stephan Wink** - *Initial work* - [WShield](https://github.com/winkste)

TBD: See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* TBD

