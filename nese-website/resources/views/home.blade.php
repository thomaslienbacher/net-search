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
                            @if (session('status'))
                                <div class="alert alert-success" role="alert">
                                    {{ session('status') }}
                                </div>
                            @endif
                            <div id="room-table">
                                <table style="width:100%">
                                    <thead>
                                    <tr>
                                        <th class="sort" data-sort="room-id">Raum ID</th>
                                        <th class="sort" data-sort="room-name">Benennung</th>
                                        <th>
                                            <input type="text" class="search" placeholder="Suchen..."/>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody class="list">
                                    @foreach($room as $r)
                                        <tr>
                                            <td class="room-id">{{ $r->id_room }}</td>
                                            <td class="room-name">{{ $r->name }}</td>
                                            <td class="room-edit">
                                                <button class="room-edit-btns">Ändern</button>
                                            </td>
                                            <td class="room-remove">
                                                <button class="room-remove-btns">Löschen</button>
                                            </td>
                                        </tr>
                                    @endforeach
                                    </tbody>
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

    <div class="test">
        <div id="contacts">
            <table>
                <thead>
                <tr>
                    <th class="sort" data-sort="name">Name</th>
                    <th class="sort" data-sort="age">Age</th>
                    <th class="sort" data-sort="city">City</th>
                    <th colspan="2">
                        <input type="text" class="search" placeholder="Search contact"/>
                    </th>
                </tr>
                </thead>
                <tbody class="list">
                <tr>
                    <td class="id" style="display:none;">1</td>
                    <td class="name">Jonny</td>
                    <td class="age">27</td>
                    <td class="city">Stockholm</td>
                    <td class="edit">
                        <button class="edit-item-btn">Edit</button>
                    </td>
                    <td class="remove">
                        <button class="remove-item-btn">Remove</button>
                    </td>
                </tr>
                <tr>
                    <td class="id" style="display:none;">2</td>
                    <td class="name">Jonas</td>
                    <td class="age">-132</td>
                    <td class="city">Berlin</td>
                    <td class="edit">
                        <button class="edit-item-btn">Edit</button>
                    </td>
                    <td class="remove">
                        <button class="remove-item-btn">Remove</button>
                    </td>
                </tr>
                <tr>
                    <td class="id" style="display:none;">3</td>
                    <td class="name">Gustaf</td>
                    <td class="age">-23</td>
                    <td class="city">Sundsvall</td>
                    <td class="edit">
                        <button class="edit-item-btn">Edit</button>
                    </td>
                    <td class="remove">
                        <button class="remove-item-btn">Remove</button>
                    </td>
                </tr>
                <tr>
                    <td class="id" style="display:none;">4</td>
                    <td class="name">Fredrik</td>
                    <td class="age">26</td>
                    <td class="city">Goteborg</td>
                    <td class="edit">
                        <button class="edit-item-btn">Edit</button>
                    </td>
                    <td class="remove">
                        <button class="remove-item-btn">Remove</button>
                    </td>
                </tr>
                </tbody>
            </table>
            <table>
                <td class="name">
                    <input type="hidden" id="id-field"/>
                    <input type="text" id="name-field" placeholder="Name"/>
                </td>
                <td class="age">
                    <input type="text" id="age-field" placeholder="Age"/>
                </td>
                <td class="city">
                    <input type="text" id="city-field" placeholder="City"/>
                </td>
                <td class="add">
                    <button id="add-btn">Add</button>
                    <button id="edit-btn">Edit</button>
                </td>
            </table>
        </div>
    </div>

    <script>
        /*var options = {
            valueNames: ['room-id', 'room-name']
        };

        var roomList = new List('room-table', options);

        var roomIdField = $('#id-field'),
            roomNameField = $('#name-field'),
            roomAddBtn = $('#add-btn'),
            roomEditBtn = $('#edit-btn').hide(),
            roomEditRowBtns = $('.room-edit-btns'),
            roomRemoveBtns = $('.room-remove-btns');

        console.log('--- end ---');*/
    </script>
@endsection
