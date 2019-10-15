set ns [new Simulator]
#create file for analysis mode
set tr [open out.tr w]
$ns trace-all $tr
#create file for Animation Mode
set namtr [open out.nam w]
$ns namtrace-all $namtr
#Create Node
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
#Create Link
$ns duplex-link $n0 $n1 10Mb 5ms DropTail
$ns duplex-link $n2 $n0 10Mb 5ms DropTail
$ns duplex-link $n3 $n0 10mb 5ms DropTail
#Create Orientation
$ns duplex-link-op $n0 $n1 orient right
$ns duplex-link-op $n0 $n2 orient left-up
$ns duplex-link-op $n0 $n3 orient left-down
#create UDP Source
set udp0 [new Agent/UDP]
$ns attach-agent $n3 $udp0
#create UDP Destination
set null0 [new Agent/Null]
$ns attach-agent $n1 $null0
#connecting UDP Source & Destination
$ns connect $udp0 $null0
#create application traffic
set cbr0 [new Application/Traffic/CBR]
$cbr0 attach-agent $udp0
#Application start time
$ns at 1.0 "$cbr0 start"
#Application Stop time
$ns at 5.0 "$cbr0 stop"
#create TCP Source
set tcp0 [new Agent/TCP]
$ns attach-agent $n2 $tcp0
#create TCP Destination
set sink0 [new Agent/TCPSink]
$ns attach-agent $n1 $sink0
$ns connect $tcp0 $sink0
#create application traffic
set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0
#Application start & stop time
$ns at 2.0 "$ftp0 start"
$ns at 5.0 "$ftp0 stop"

$ns at 10.0 "$ns halt"
$ns run
