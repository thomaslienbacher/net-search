<?php

namespace App\Http\Controllers;

use http\Message\Body;
use Illuminate\Http\Request;
use GuzzleHttp\Client;

class HomeController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        $this->middleware('auth');
    }

    /**
     * Show the application dashboard.
     *
     * @return \Illuminate\Contracts\Support\Renderable
     */
    public function index()
    {
        $options = [
            'headers' => ['API_TOKEN' => env('API_TOKEN')]
        ];

        $client = new Client();
        $res = $client->get('http://localhost:8100/nese_rest_api_war/api/search', $options);
        $switches = $client->get('http://localhost:8100/nese_rest_api_war/api/switches', $options);
        $rooms = $client->get('http://localhost:8100/nese_rest_api_war/api/rooms', $options);
        $devices = $client->get('http://localhost:8100/nese_rest_api_war/api/devices', $options);
        $connections = $client->get('http://localhost:8100/nese_rest_api_war/api/portconnections', $options);

        return view('home', ['search' => json_decode($res->getBody()),
            'switch' => json_decode($switches->getBody()),
            'room' => json_decode($rooms->getBody()),
            'device' => json_decode($devices->getBody()),
            'connection' => json_decode($connections->getBody()),
        ]);
    }


}
