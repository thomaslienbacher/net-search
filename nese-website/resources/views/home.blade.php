@extends('layouts.app')

@section('content')
    <div class="container">
        <div class="row justify-content-center">

            <div id="table-list">
                <div id="search-results">
                    <div class="pt-5"></div>
                    <div class="card">
                        <div class="card-header">Liste von Räumen mit einem Gerät
                            <input type="text" class="search" placeholder="Suchen"/>
                            <button class="btn btn-primary" data-sort="filter"> Sortieren nach RaumNr</button>
                        </div>
                        <div class="card-body">
                            @if (session('status'))
                                <div class="alert alert-success" role="alert">
                                    {{ session('status') }}
                                </div>
                            @endif

                            <table style="width:100%">
                                <tr>
                                    <th class="sort" data-sort="raumnr">Raum ID</th>
                                    <th class="sort" data-sort="geraetename">Name des Geräts</th>
                                    <th class="sort" data-sort="macadress">MAC-Adresse des Geräts</th>
                                </tr>
                                @foreach($search as $h)
                                    <tr>
                                        <td class="roomname">{{ $h->room->name }}</td>
                                        <td class="devicename">{{ $h->device->name }}</td>
                                        <td class="devicemac">{{ $h->device->mac }}</td>
                                    </tr>
                                @endforeach
                            </table>


                        </div>
                    </div>
                </div>

                <div id="rooms">
                    <div class="pt-5"></div>
                    <div class="card">
                        <div class="card-header">R&auml;ume
                            <button type="submit" value="Submit">Löschen</button>
                            <button type="submit" value="Submit">Hinzufügen</button>
                            <button type="submit" value="Submit">Bearbeiten</button>

                        </div>
                        <div class="card-body">
                            @if (session('status'))
                                <div class="alert alert-success" role="alert">
                                    {{ session('status') }}
                                </div>
                            @endif
                            <div>
                                <table style="width:100%">
                                    <tr>
                                        <th class="sort" data-sort="raumid">Raum ID</th>
                                        <th class="sort" data-sort="raumnr">Benennung</th>
                                    </tr>
                                    @foreach($room as $r)
                                        <tr>
                                            <td class="idroom">{{ $r->id_room }}</td>
                                            <td class="raumname">{{ $r->name }}</td>
                                        </tr>
                                    @endforeach
                                </table>
                            </div>

                        </div>
                    </div>
                </div>

                <div id="devices">
                    <div class="pt-5"></div>
                    <div class="card">
                        <div class="card-header">Ger&auml;te
                            <input type="text" class="search" placeholder="Suchen"/>
                            <button type="submit" value="Submit">Löschen</button>
                            <button type="submit" value="Submit">Hinzufügen</button>
                            <button type="submit" value="Submit">Bearbeiten</button>

                        </div>
                        <div class="card-body">
                            @if (session('status'))
                                <div class="alert alert-success" role="alert">
                                    {{ session('status') }}
                                </div>
                            @endif
                            <div>
                                <table style="width:100%">
                                    <tr>
                                        <th>Gerät ID</th>
                                        <th>Name</th>
                                        <th>MAC-Adresse</th>
                                    </tr>
                                    @foreach($device as $d)
                                        <tr>
                                            <td>{{ $d->id_device }}</td>
                                            <td>{{ $d->name }}</td>
                                            <td>{{ $d->mac }}</td>
                                        </tr>
                                    @endforeach
                                </table>
                            </div>

                        </div>
                    </div>
                </div>

                <div id="switches">
                    <div class="pt-5"></div>
                    <div class="card">
                        <div class="card-header">Switches
                            <button type="submit" value="Submit">Löschen</button>
                            <button type="submit" value="Submit">Hinzufügen</button>
                            <button type="submit" value="Submit">Bearbeiten</button>
                        </div>
                        <div class="card-body">
                            @if (session('status'))
                                <div class="alert alert-success" role="alert">
                                    {{ session('status') }}
                                </div>
                            @endif
                            <div>
                                <table style="width:100%">
                                    <tr>
                                        <th>Switch ID</th>
                                        <th>Name</th>
                                        <th>IP-Adresse</th>
                                        <th>Community String</th>
                                    </tr>
                                    @foreach($switch as $s)
                                        <tr>
                                            <td>{{ $s->id_switch }}</td>
                                            <td>{{ $s->name }}</td>
                                            <td>{{ $s->ip }}</td>
                                            <td>{{ $s->community_string }}</td>
                                        </tr>
                                    @endforeach
                                </table>
                            </div>

                        </div>
                    </div>
                </div>

                <div id="portconnections">
                    <div class="pt-5"></div>
                    <div class="card">
                        <div class="card-header">Port Connections
                            <button type="submit" value="Submit">Löschen</button>
                            <button type="submit" value="Submit">Hinzufügen</button>
                            <button type="submit" value="Submit">Bearbeiten</button>
                        </div>
                        <div class="card-body">
                            @if (session('status'))
                                <div class="alert alert-success" role="alert">
                                    {{ session('status') }}
                                </div>
                            @endif
                            <div>
                                <table style="width:100%">
                                    <tr>
                                        <th>Port Connection ID</th>
                                        <th>Switch ID</th>
                                        <th>Raum ID</th>
                                        <th>Port Nr.</th>
                                    </tr>
                                    @foreach($connection as $c)
                                        <tr>
                                            <td class="idportconnection">{{ $c->id_port_connection }}</td>
                                            <td class="switchid">{{ $c->switch_id }}</td>
                                            <td class="roomid">{{ $c->room_id }}</td>
                                            <td class="port">{{ $c->port }}</td>
                                        </tr>
                                    @endforeach
                                </table>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        var options = {
            valueNames1: ['filter', 'geraetename'],
            valueNames2: ['geraet', 'geraetename']
        };


        var listeList = new List('liste', options);
        var raumList = new List('raum', options);

        var idField = $('#id-field'),
            geraeteField = $('#geraete-field'),
            geraetenameField = $('#geraetename-field'),
            macadressField = $('#macadress-field');


    </script>
@endsection
