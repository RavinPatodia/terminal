(function($)
{
    var cometd = $.cometd;

    $(document).ready(function()
    {
        function _connectionEstablished()
        {
            $('#body').append('<div>CometD Connection Established</div>');
        }

        function _connectionBroken()
        {
            $('#body').append('<div>CometD Connection Broken</div>');
        }

        function _connectionClosed()
        {
            $('#body').append('<div>CometD Connection Closed</div>');
        }

        // Function that manages the connection status with the Bayeux server
        var _connected = false;
        function _metaConnect(message)
        {
            if (cometd.isDisconnected())
            {
                _connected = false;
                _connectionClosed();
                return;
            }

            var wasConnected = _connected;
            _connected = message.successful === true;
            if (!wasConnected && _connected)
            {
                _connectionEstablished();
            }
            else if (wasConnected && !_connected)
            {
                _connectionBroken();
            }
        }

        // Function invoked when first contacting the server and
        // when the server has lost the state of this client
        function _metaHandshake(message)
        {
        	if (message.successful)
            {
                $('#status').append('<div>CometD handshake successful</div>');
                cometd.subscribe('/echo1', function(message)
                        {
                        	var data = message.data;
                            var value = data.jsonStr;
                        	alert(value);

                            // Find the div for the given stock symbol
                         
                        });
            }
            else
            {
                $('#status').append('<div>CometD handshake failed</div>');
            }
            	
        }

        // Disconnect when the page unloads
        $(window).unload(function()
        {
            cometd.disconnect(true);
        });

        var cometURL = location.protocol + "//" + location.host + config.contextPath + "/cometd";
        cometd.configure({
        	url: 'http://localhost:8080/terminal/cometd',
            //url: cometURL,
            //logLevel: 'debug'
        });

        cometd.addListener('/meta/handshake', _metaHandshake);//监听handshake是否成功
       // cometd.addListener('/meta/connect', _metaConnect);
        
       
        cometd.handshake();
        
    });
})(jQuery);
