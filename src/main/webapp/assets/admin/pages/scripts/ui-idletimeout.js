var UIIdleTimeout = function () {

    return {

        //main function to initiate the module
        init: function () {

            // cache a reference to the countdown element so we don't have to query the DOM for it on each ping.
            var $countdown;

            $('body').append(
            '<div class="modal fade" id="idle-timeout-dialog" data-backdrop="static">'+
    		'<div class="modal-dialog modal-small">'+
    			'<div class="modal-content">'+
    			'<div class="modal-header">'+
    				'<h4 class="modal-title">您的操作已过时</h4>'+
    			'</div>'+
    			'<div class="modal-body">'+
    				'<p><i class="fa fa-warning"></i> 您的账户将退出系统 ，<span id="idle-timeout-counter"></span> 秒</p>'+
    				'<p>您想继续操作?</p>'+
    				'</div>'+
    			'<div class="modal-footer">'+
    				'<button id="idle-timeout-dialog-logout" type="button" class="btn btn-default">不, 退出</button>'+
    				'<button id="idle-timeout-dialog-keepalive" type="button" class="btn btn-primary" data-dismiss="modal">是, 继续操作</button>'+
    			'</div>'+
    		'</div></div></div>');
                    
            // start the idle timer plugin
            $.idleTimeout('#idle-timeout-dialog', '.modal-content button:last', {
                idleAfter: 1200000, // 1200 seconds
                timeout: 30000, //30 seconds to timeout
                pollingInterval: 1200000, // 1200 seconds
                keepAliveURL: 'demo/idletimeout_keepalive.php',
                serverResponseEquals: 'OK',
                onTimeout: function(){
                    window.location = "views/lock.jsp";
                },
                onIdle: function(){
                    $('#idle-timeout-dialog').modal('show');
                    $countdown = $('#idle-timeout-counter');

                    $('#idle-timeout-dialog-keepalive').on('click', function () { 
                        $('#idle-timeout-dialog').modal('hide');
                    });

                    $('#idle-timeout-dialog-logout').on('click', function () { 
                        $('#idle-timeout-dialog').modal('hide');
                        $.idleTimeout.options.onTimeout.call(this);
                    });
                },
                onCountdown: function(counter){
                    $countdown.html(counter); // update the counter
                }
            });
            
        }

    };

}();