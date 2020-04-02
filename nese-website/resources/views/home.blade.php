@extends('layouts.app')

@section('content')
    <div class="container-fluid">
        <div class="row">
            <div id="table-list" class="col-md-8 offset-md-2">
                <div id="search-results">
                    <div class="pt-5"></div>
                    <div class="card">
                        <div class="card-header">
                            Liste von Räumen mit einem Gerät
                        </div>
                        <div class="card-body">
                            <table style="width:100%">
                                <tr>
                                    <th>Raum ID</th>
                                    <th>Name des Geräts</th>
                                    <th>MAC-Adresse des Geräts</th>
                                </tr>
                                @foreach($search as $h)
                                    <tr>
                                        <td>{{ $h->room->name }}</td>
                                        <td>{{ $h->device->name }}</td>
                                        <td>{{ $h->device->mac }}</td>
                                    </tr>
                                @endforeach
                            </table>


                        </div>
                    </div>
                </div>

                <div class="rooms">
                    <div class="pt-5"></div>
                    <div class="card">
                        <div class="card-header">
                            R&auml;ume
                        </div>
                        <div class="card-body">
                            <div id="room_table">
                                <table style="width:100%">
                                    <thead>
                                    <tr>
                                        <th class="sort" data-sort="id">Raum ID</th>
                                        <th class="sort" data-sort="name">Benennung</th>
                                        <th>
                                            <input type="text" class="search" placeholder="Suchen..."/>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody class="list">
                                    @foreach($room as $r)
                                        <tr>
                                            <td class="id">{{ $r->id_room }}</td>
                                            <td class="name">{{ $r->name }}</td>
                                            <td class="edit">
                                                <button class="room_edit_btns">Ändern</button>
                                            </td>
                                            <td class="remove">
                                                <button class="room_remove_btns">Löschen</button>
                                            </td>
                                        </tr>
                                    @endforeach
                                    </tbody>
                                </table>
                                <table>
                                    <td class="room_name">
                                        <input type="hidden" id="room_id_field"/>
                                        <input type="text" id="room_name_field" placeholder="Name"/>
                                    </td>
                                    <td class="add">
                                        <button id="room_add_btn">Add</button>
                                        <button id="room_edit_btn">Edit</button>
                                    </td>
                                </table>
                            </div>

                        </div>
                    </div>
                </div>

                <div id="devices">
                    <div class="pt-5"></div>
                    <div class="card">
                        <div class="card-header">
                            Ger&auml;te
                        </div>
                        <div class="card-body">
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
                        <div class="card-header">
                            Switches
                        </div>
                        <div class="card-body">
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
                        <div class="card-header">
                            Port Connections
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
@endsection
