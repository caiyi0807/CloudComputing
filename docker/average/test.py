import unittest
import average


class TestAverage(unittest.TestCase):
    def test_average(self):
        self.assertEqual(average.averageMark(200, 4), 50)
        self.assertEqual(average.averageMark(0, 0), 0)



if __name__ == '__main__':
    unittest.main()
