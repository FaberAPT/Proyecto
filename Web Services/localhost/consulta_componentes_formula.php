<?PHP
    $hostname = "localhost";
    $database = "grupo_9_castuera_db";
    $username = "root";
    $password = "";
    
    $json = array();

    if(isset($_GET["parametro_1"]))
    {
        $id_formula = $_GET['parametro_1'];

        $conexion = mysqli_connect($hostname, $username, $password, $database);

        $consulta = "SELECT id_formula_componente, id_formula, componente, porcentaje, orden, ".
                           "CASE dosificacion WHEN 0 THEN 'No' WHEN 1 THEN 'Si' END AS dosificacion, ".
                           "tipo, estado FROM formula_componente WHERE id_formula = '{$id_formula}' ORDER BY orden";
        $resultado = mysqli_query($conexion, $consulta);

        while($registro = mysqli_fetch_array($resultado))
        {
            $json['componente'][] = $registro;
        }
        
        mysqli_close($conexion);
        echo json_encode($json);
    }
    else
    {
        $results["success"] = 0;
		$results["message"] = 'Web Service no retorna';
		$json['datos'][] = $results;
		echo json_encode($json);
    }
?>

