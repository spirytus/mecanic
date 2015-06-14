package agh.mgr.mecanic;
import static agh.mgr.mecanic.TestHelper.areEqualEdges;
import static java.util.Arrays.*;

import agh.mgr.mecanic.misc.tools.SerializableScanHistory;
import org.junit.Test;
import pl.edu.agh.amber.hokuyo.MapPoint;

import java.util.List;

import static org.junit.Assert.*;

public class PositionerTest {

    public static final String SCAN_UNDER_TEST = "185151911";

    @Test
    public void testFindEdgesScan0() throws Exception {
        List<List<MapPoint>> history = SerializableScanHistory.loadScans(SCAN_UNDER_TEST);
        List<MapPoint> mapPoints = history.get(0);
        List<List<Integer>> computedEdges = Positioner.findEdges(mapPoints);
        List<List<Integer>> expectedEdges = asList(
                asList(223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250),
                asList(428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456)
        );
        assertTrue(areEqualEdges(expectedEdges, computedEdges));
    }

    // TODO : Scalanie bliskich krawędzi - gdy bardzo zaszumione przy odczyty przy krawędzi. Jesli po zmergowaniu >3 krawedzi -> do kosza.
    @Test
    public void testFindEdgesScan1() throws Exception {
        List<List<MapPoint>> history = SerializableScanHistory.loadScans(SCAN_UNDER_TEST);
        List<MapPoint> mapPoints = history.get(1);
        List<List<Integer>> computedEdges = Positioner.findEdges(mapPoints);
        List<List<Integer>> expectedEdges = asList(
                asList(76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104),
                asList(335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361),
                asList(607, 608, 609, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625),
                asList(630, 631, 632, 633, 634, 635, 636, 637, 638),
                asList(646, 647, 648)
        );
        assertTrue(areEqualEdges(expectedEdges, computedEdges));
    }

    //To jest na granicy sensownego odczytu - dużo zaszumien
    @Test
    public void testFindEdgesScan2() throws Exception {
        List<List<MapPoint>> history = SerializableScanHistory.loadScans(SCAN_UNDER_TEST);
        List<MapPoint> mapPoints = history.get(2);
        List<List<Integer>> computedEdges = Positioner.findEdges(mapPoints);
        List<List<Integer>> expectedEdges = asList(
                asList(164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191),
                asList(460, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 481, 482, 483, 484, 485, 486),
                asList(623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643)
        );
        assertTrue(areEqualEdges(expectedEdges, computedEdges));
    }

    @Test
    public void testFindEdgesScan3() throws Exception {
        List<List<MapPoint>> history = SerializableScanHistory.loadScans(SCAN_UNDER_TEST);
        List<MapPoint> mapPoints = history.get(3);
        List<List<Integer>> computedEdges = Positioner.findEdges(mapPoints);
        List<List<Integer>> expectedEdges = asList(
                asList(58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86),
                asList(303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324),
                asList(532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560)
        );
        assertTrue(areEqualEdges(expectedEdges, computedEdges));
    }
    @Test
    public void testFindEdgesScan4() throws Exception {
        List<List<MapPoint>> history = SerializableScanHistory.loadScans(SCAN_UNDER_TEST);
        List<MapPoint> mapPoints = history.get(4);
        List<List<Integer>> computedEdges = Positioner.findEdges(mapPoints);
        List<List<Integer>> expectedEdges = asList(
                asList(187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211),
                asList(455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, 481)
        );
        assertTrue(areEqualEdges(expectedEdges, computedEdges));
    }
    @Test
    public void testFindEdgesScan5() throws Exception {
        List<List<MapPoint>> history = SerializableScanHistory.loadScans(SCAN_UNDER_TEST);
        List<MapPoint> mapPoints = history.get(5);
        List<List<Integer>> computedEdges = Positioner.findEdges(mapPoints);
        List<List<Integer>> expectedEdges = asList(
                asList(21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 42, 43, 44, 45),
                asList(316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341),
                asList(624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652)
        );
        assertTrue(areEqualEdges(expectedEdges, computedEdges));
    }
    @Test
    public void testFindEdgesScan6() throws Exception {
        List<List<MapPoint>> history = SerializableScanHistory.loadScans(SCAN_UNDER_TEST);
        List<MapPoint> mapPoints = history.get(6);
        List<List<Integer>> computedEdges = Positioner.findEdges(mapPoints);
        List<List<Integer>> expectedEdges = asList(
                asList(197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224),
                asList(460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, 481, 482, 483, 484, 485, 486, 487)
        );
        assertTrue(areEqualEdges(expectedEdges, computedEdges));
    }
    @Test
    public void testFindEdgesScan7() throws Exception {
        List<List<MapPoint>> history = SerializableScanHistory.loadScans(SCAN_UNDER_TEST);
        List<MapPoint> mapPoints = history.get(7);
        List<List<Integer>> computedEdges = Positioner.findEdges(mapPoints);
        List<List<Integer>> expectedEdges = asList(
                asList(59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86),
                asList(319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347),
                asList(565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592)
        );

        assertTrue(areEqualEdges(expectedEdges, computedEdges));

    }
}