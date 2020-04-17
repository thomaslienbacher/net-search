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
                            <div id="list_table">
                                <table style="width:100%">
                                    <thead>
                                    <tr>
                                        <th class="sort" data-sort="id">Raum ID</th>
                                        <th class="sort" data-sort="name">Name des Geräts</th>
                                        <th class="sort" data-sort="macadress">MAC-Adresse des Geräts</th>
                                        <th>
                                            <input type="text" class="search" placeholder="Suchen..."/>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody class="list">
                                    @foreach($search as $h)
                                        <tr>
                                            <td class="id">{{ $h->room->name }}</td>
                                            <td class="name">{{ $h->device->name }}</td>
                                            <td class="macadress">{{ $h->device->mac }}</td>
                                        </tr>
                                    @endforeach
                                    <!-- TestDaten
                                    <tr>
                                        <td class="id">atest</td>
                                        <td class="name">btest</td>
                                        <td class="macadress">ctest</td>
                                    </tr>
                                    <tr>
                                        <td class="id">dtest</td>
                                        <td class="name">etest</td>
                                        <td class="macadress">ftest</td>
                                    </tr>
                                    -->
                                    </tbody>
                                </table>

                            </div>


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
                                <div id="geraete_table">
                                    <table style="width:100%">
                                        <thead>
                                        <tr>
                                            <th class="sort" data-sort="id">Gerät ID</th>
                                            <th class="sort" data-sort="name">Name</th>
                                            <th class="sort" data-sort="macaddress">MAC-Adresse</th>
                                            <th>
                                                <input type="text" class="search" placeholder="Suchen..."/>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody class="list">
                                        @foreach($device as $d)
                                            <tr>
                                                <td class="id">{{ $d->id_device }}</td>
                                                <td class="name">{{ $d->name }}</td>
                                                <td class="macaddress">{{ $d->mac }}</td>
                                                <td class="edit">
                                                    <button class="geraete_edit_btns">Ändern</button>
                                                </td>
                                                <td class="remove">
                                                    <button class="geraete_remove_btns">Löschen</button>
                                                </td>
                                            </tr>
                                        @endforeach
                                        </tbody>
                                    </table>
                                    <table>
                                        <td class="geraete_name">
                                            <input type="hidden" id="geraete_id_field"/>
                                            <input type="text" id="geraete_name_field" placeholder="Name"/>
                                            <input type="text" id="geraete_macadresse_field" placeholder="MAC-Address"/>
                                        </td>
                                        <td class="add">
                                            <button id="geraete_add_btn">Add</button>
                                            <button id="geraete_edit_btn">Edit</button>
                                        </td>
                                    </table>

                                </div>
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
                                <div id="switch_table">
                                    <table style="width:100%">
                                        <thead>
                                        <tr>
                                            <th class="sort" data-sort="id">Switch ID</th>
                                            <th class="sort" data-sort="name">Name</th>
                                            <th class="sort" data-sort="ipaddress">IP-Adresse</th>
                                            <th class="sort" data-sort="communitystring">Community String</th>
                                            <th>
                                                <input type="text" class="search" placeholder="Suchen..."/>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody class="list">
                                        @foreach($switch as $s)
                                            <tr>
                                                <td class="id">{{ $s->id_switch }}</td>
                                                <td class="name">{{ $s->name }}</td>
                                                <td class="ipaddress">{{ $s->ip }}</td>
                                                <td class="communitystring">{{ $s->community_string }}</td>
                                                <td class="edit">
                                                    <button class="switch_edit_btns">Ändern</button>
                                                </td>
                                                <td class="remove">
                                                    <button class="switch_remove_btns">Löschen</button>
                                                </td>
                                            </tr>
                                        @endforeach
                                        </tbody>
                                    </table>
                                    <table>
                                        <td class="switch_name">
                                            <input type="hidden" id="switch_id_field"/>
                                            <input type="text" id="switch_name_field" placeholder="Name"/>
                                            <input type="text" id="switch_ipaddress_field" placeholder="IP-Adresse"/>
                                            <input type="text" id="switch_communitystring_field" placeholder="Community-String"/>
                                        </td>
                                        <td class="add">
                                            <button id="switch_add_btn">Add</button>
                                            <button id="switch_edit_btn">Edit</button>
                                        </td>
                                    </table>

                                </div>
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
                            <div>
                                <div id="port_table">
                                    <table style="width:100%">
                                        <thead>
                                        <tr>
                                            <th class="sort" data-sort="id">Port Connection ID</th>
                                            <th class="sort" data-sort="switchid">Switch ID</th>
                                            <th class="sort" data-sort="roomid">Raum ID</th>
                                            <th class="sort" data-sort="portnr">Port Nr.</th>
                                            <th>
                                                <input type="text" class="search" placeholder="Suchen..."/>
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody class="list">
                                        @foreach($connection as $c)
                                            <tr>
                                                <td class="id">{{ $c->id_port_connection }}</td>
                                                <td class="switchid">{{ $c->switch_id }}</td>
                                                <td class="roomid">{{ $c->room_id }}</td>
                                                <td class="portnr">{{ $c->port }}</td>
                                                <td class="edit">
                                                    <button class="port_edit_btns">Ändern</button>
                                                </td>
                                                <td class="remove">
                                                    <button class="port_remove_btns">Löschen</button>
                                                </td>
                                            </tr>
                                        @endforeach
                                        </tbody>
                                    </table>
                                    <table>
                                        <td class="port_name">
                                            <input type="hidden" id="port_id_field"/>
                                            <input type="text" id="connection_switch_id_field" placeholder="Switch-ID"/>
                                            <input type="text" id="connection_room_id_field" placeholder="Raum-ID"/>
                                            <input type="text" id="port_nr_field" placeholder="Port-Nr"/>
                                        </td>
                                        <td class="add">
                                            <button id="port_add_btn">Add</button>
                                            <button id="port_edit_btn">Edit</button>
                                        </td>
                                    </table>

                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection
