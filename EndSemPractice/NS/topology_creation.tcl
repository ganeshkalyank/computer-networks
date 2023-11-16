set ns [new Simulator]

set tf [open topo_creation.tr w]
$ns trace-all $tf

set nf [open topo_creation.nam w]
$ns namtrace-all $nf

proc finish {} {
  global ns tf nf
  $ns flush-trace
  close $tf
  close $nf
  exec nam topo_creation.nam &
  exit 0
}

for {set i 0} {$i < 6} {incr i} {
  set n($i) [$ns node]
}

$ns duplex-link $n(0) $n(2) 10Mb 10ms DropTail
$ns duplex-link $n(1) $n(2) 10Mb 10ms DropTail
$ns duplex-link $n(2) $n(3) 10Mb 10ms DropTail
$ns duplex-link $n(3) $n(4) 10Mb 10ms DropTail
$ns duplex-link $n(3) $n(5) 10Mb 10ms DropTail

$ns queue-limit $n(0) $n(2) 10
$ns queue-limit $n(1) $n(2) 10
$ns queue-limit $n(2) $n(3) 10
$ns queue-limit $n(3) $n(4) 10
$ns queue-limit $n(3) $n(5) 10

$ns duplex-link-op $n(0) $n(2) orient right-down
$ns duplex-link-op $n(1) $n(2) orient right-up
$ns duplex-link-op $n(2) $n(3) orient right
$ns duplex-link-op $n(3) $n(4) orient right-up
$ns duplex-link-op $n(3) $n(5) orient right-down

set tcp [new Agent/TCP]
$ns attach-agent $n(0) $tcp

set sink [new Agent/TCPSink]
$ns attach-agent $n(4) $sink

$ns connect $tcp $sink

set ftp [new Application/FTP]
$ftp attach-agent $tcp
$ftp set type_ FTP

set udp [new Agent/UDP]
$ns attach-agent $n(1) $udp

set null [new Agent/Null]
$ns attach-agent $n(5) $null

$ns connect $udp $null

set cbr [new Application/Traffic/CBR]
$cbr attach-agent $udp

$ns at 0.1 "$ftp start"
$ns at 0.2 "$cbr start"
$ns at 5.0 "$ftp stop"
$ns at 5.1 "$cbr stop"
$ns at 5.2 "finish"

$ns run
